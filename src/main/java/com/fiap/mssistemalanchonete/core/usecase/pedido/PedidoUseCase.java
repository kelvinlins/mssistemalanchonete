package com.fiap.mssistemalanchonete.core.usecase.pedido;

import com.fiap.mssistemalanchonete.core.exception.exception.ComboNotFoundException;
import com.fiap.mssistemalanchonete.core.exception.exception.PedidoNotFoundException;
import com.fiap.mssistemalanchonete.core.model.Combo;
import com.fiap.mssistemalanchonete.core.model.Pedido;
import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.model.StatusPedidoEnum;
import com.fiap.mssistemalanchonete.core.port.PedidoPort;
import com.fiap.mssistemalanchonete.core.usecase.PedidoUseCaseFacade;
import com.fiap.mssistemalanchonete.core.validation.pedido.PedidoValidation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PedidoUseCase implements PedidoUseCaseFacade {

  private final PedidoPort pedidoPort;
  private final PedidoValidation validation;

  @Autowired
  public PedidoUseCase(PedidoPort pedidoPort, PedidoValidation validation){
    this.pedidoPort = pedidoPort;
    this.validation = validation;
  }

  @Override
  public Pedido criarPedido(Pedido pedido) throws Exception {
    validation.validarPedido(pedido);
    pedido.setCodigo(UUID.randomUUID().toString());
    pedido.setStatus(StatusPedidoEnum.INICIADO);
    pedido.setCombos(List.of(Combo.builder().id(1).build()));
    return pedidoPort.salvarPedido(pedido);
  }

  @Override
  public Pedido atualizarPedido(Pedido pedido, String codigo) throws Exception {
    getPedidoPorCodigo(codigo);
    pedido.setCodigo(codigo);
    return pedidoPort.atualizarPedido(pedido);
  }

  @Override
  public void deletarPedido(String codigo) throws Exception {
    Pedido pedido = getPedidoPorCodigo(codigo);
    pedidoPort.deletarPedido(pedido);
  }

  @Override
  public Pedido adicionarProdutos(String codigoPedido, Integer codigoCombo, String codigoProduto, Integer quantidade) throws Exception {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarAlteracaoCombosPedido(pedido, codigoProduto, quantidade);

    pedido.getCombos().stream()
      .filter(combo -> codigoCombo.equals(combo.getId()))
      .findFirst()
      .ifPresentOrElse(
        combo -> {
          var produtoKey = combo.getItens().keySet().stream()
            .filter(produto ->StringUtils.equals(produto.getCodigo(), codigoProduto))
            .findFirst();
          produtoKey.ifPresentOrElse(
            produto -> combo.getItens().put(produto, combo.getItens().get(produto)+quantidade),
            () -> combo.getItens().put(Produto.builder().codigo(codigoProduto).build(), quantidade)
          );
        },
        () -> {
          throw new ComboNotFoundException();
        }
      );

    return atualizarPedido(pedido, codigoPedido);
  }

  @Override
  public Pedido removerProduto(String codigoPedido, Integer comboId, String codigoProduto, Integer quantidade) throws Exception {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarAlteracaoCombosPedido(pedido, codigoProduto, quantidade);

    pedido.getCombos().stream()
      .filter(combo -> comboId.equals(combo.getId()))
      .findFirst()
      .ifPresentOrElse(
        combo -> {
          var produtoKey = combo.getItens().keySet().stream()
            .filter(produto ->StringUtils.equals(produto.getCodigo(), codigoProduto))
            .findFirst();
          produtoKey.ifPresent(
            produto -> {
              if (quantidade<combo.getItens().get(produto)){
                combo.getItens().put(produto, combo.getItens().get(produto)-quantidade);
              } else {
                combo.getItens().remove(produto);
              }
            }
          );
        },
        () -> {
          throw new ComboNotFoundException();
        }
      );
    return atualizarPedido(pedido, codigoPedido);
  }

  @Override
  public Page<Pedido> listarPedidos(Pageable pageable, List<StatusPedidoEnum> status) {
    return pedidoPort.listarPedidos(pageable, status);
  }

  @Override
  public Integer adicionarCombo(String codigoPedido, Pedido pedidoParaAtualizar) {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarStatusAlteracaoCombo(pedido);
    validation.validarCombos(pedidoParaAtualizar.getCombos());

    var maiorComboId = pedido.getCombos().stream()
      .filter(Objects::nonNull)
      .map(Combo::getId)
      .max(Integer::compareTo);

    Combo comboParaAdiconar = getComboParaAdiconar(pedidoParaAtualizar);

    comboParaAdiconar.setId(1 + maiorComboId.orElse(0));
    pedido.getCombos().add(comboParaAdiconar);
    pedidoPort.atualizarPedido(pedido);
    return comboParaAdiconar.getId();
  }

  @Override
  public Pedido getPedidoPorCodigo(String codigoPedido) {
    return null;
  }

  private static Combo getComboParaAdiconar(Pedido pedidoParaAtualizar) {
    return Objects.isNull(pedidoParaAtualizar.getCombos()) || pedidoParaAtualizar.getCombos().isEmpty() ?
      new Combo() :
      pedidoParaAtualizar.getCombos().get(0);
  }

  @Override
  public Pedido checkout(String codigoPedido) {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarStatusAlteracaoCombo(pedido);
    validation.validarQuePedidoTemProdutos(pedido);

    pedido.getCombos().removeIf(Combo::semProdutos);
    pedido.setHoraCheckout(LocalDateTime.now());
    pedido.setStatus(StatusPedidoEnum.RECEBIDO);

    return pedidoPort.atualizarPedido(pedido);
  }

  @Override
  public void deletarCombo(String codigoPedido, Integer comboId) {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarStatusAlteracaoCombo(pedido);

    if (Objects.nonNull(pedido.getCombos())){
      var foiRemovido = pedido.getCombos().removeIf(combo -> comboId.equals(combo.getId()));
      if (Boolean.FALSE.equals(foiRemovido)){
        throw new ComboNotFoundException();
      }
      pedidoPort.atualizarPedido(pedido);
    } else {
      throw new ComboNotFoundException();
    }
  }
}
