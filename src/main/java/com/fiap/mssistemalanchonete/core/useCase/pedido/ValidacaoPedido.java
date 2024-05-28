package com.fiap.mssistemalanchonete.core.useCase.pedido;

import com.fiap.mssistemalanchonete.core.domain.error.exception.PedidoSemProdutosException;
import com.fiap.mssistemalanchonete.core.domain.error.exception.QuantidadeInvalidaException;
import com.fiap.mssistemalanchonete.core.domain.error.exception.StatusInvalidoException;
import com.fiap.mssistemalanchonete.core.domain.model.Combo;
import com.fiap.mssistemalanchonete.core.domain.model.Pedido;
import com.fiap.mssistemalanchonete.core.domain.model.StatusPedidoEnum;
import com.fiap.mssistemalanchonete.core.useCase.cliente.ClienteUseCase;
import com.fiap.mssistemalanchonete.core.useCase.produto.ProdutoUseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ValidacaoPedido {
  private final ClienteUseCase clienteUserCase;
  private final ProdutoUseCase produtoUserCase;

  public ValidacaoPedido(ClienteUseCase clienteUserCase, ProdutoUseCase produtoUserCase) {
    this.clienteUserCase = clienteUserCase;
    this.produtoUserCase = produtoUserCase;
  }

  public void validarPedido(Pedido pedido) throws Exception {
    if (Objects.nonNull(pedido.getCliente())){
      clienteUserCase.consultarClientePorCodigo(pedido.getCliente().getCodigo());
    }
    validarCombos(pedido.getCombos());
  }

  public void validarCombos(List<Combo> combos) {
    if (Objects.nonNull(combos)){
      combos.stream()
        .filter(Objects::nonNull)
        .map(Combo::getItens)
        .filter(Objects::nonNull)
        .map(Map::entrySet)
        .forEach(
          entrySet -> entrySet.forEach(
            (entry) -> {
              validarExistenciaProduto(entry.getKey().getCodigo());
              validarQuantidade(entry.getValue());
            }
          )
        );
    }
  }

  public void validarExistenciaProduto(String codigoProduto) {
    produtoUserCase.consultarProdutoPorCodigo(codigoProduto);
  }

  public void validarQuantidade(Integer quantidade) {
    if(Objects.isNull(quantidade) || quantidade <1){
      throw new QuantidadeInvalidaException();
    }
  }

  public void validarAlteracaoCombosPedido(Pedido pedido, String codigoProduto, Integer quantidade) {
    validarStatusAlteracaoCombo(pedido);
    validarExistenciaProduto(codigoProduto);
    validarQuantidade(quantidade);
  }

  public void validarStatusAlteracaoCombo(Pedido pedido) {
    if (!StatusPedidoEnum.INICIADO.equals(pedido.getStatus())){
      throw new StatusInvalidoException();
    }
  }

  public void validarQuePedidoTemProdutos(Pedido pedido) {
    if (Boolean.TRUE.equals(pedido.semProdutos())){
      throw new PedidoSemProdutosException();
    }
  }
}
