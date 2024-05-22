package com.fiap.mssistemalanchonete.core.userCase.pedido;

import com.fiap.mssistemalanchonete.core.domain.model.Pedido;
import com.fiap.mssistemalanchonete.core.domain.model.Produto;
import com.fiap.mssistemalanchonete.core.domain.model.StatusPedidoEnum;
import com.fiap.mssistemalanchonete.core.port.PedidoPort;
import com.fiap.mssistemalanchonete.core.userCase.cliente.ClienteUserCase;
import com.fiap.mssistemalanchonete.core.userCase.produto.ProdutoUserCase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PedidoUserCase {

    private final PedidoPort pedidoPort;

    @Autowired
    public PedidoUserCase(PedidoPort pedidoPort){
        this.pedidoPort = pedidoPort;
    }

    public Pedido criarPedido(Pedido pedido) throws Exception {
        pedido.setCodigo(UUID.randomUUID().toString());
        pedido.setStatus(StatusPedidoEnum.INICIADO);
        return pedidoPort.salvarPedido(pedido);
    }

    public List<Pedido> consultarPedidoPorCategoria(String code) {
        return Collections.emptyList(); //TODO implementar
    }

    public Pedido atualizarPedido(Pedido pedido, String codigo) throws Exception {

        pedido.setCodigo(codigo);
        return pedidoPort.atualizarPedido(pedido);
    }

    public void deletarPedido(String codigo) throws Exception {

        Pedido pedido = pedidoPort.consultarPedidoPorCodigo(codigo);

        if (!ObjectUtils.isEmpty(pedido))
            throw new Exception();

        pedidoPort.deletarPedido(pedido);
    }

    public Pedido adicionarProdutos(String codigoPedido, List<String> codigoProdutos) throws Exception {
        var pedido = pedidoPort.consultarPedidoPorCodigo(codigoPedido);
        codigoProdutos.stream()
          .filter(StringUtils::isNotBlank)
            .map(codigo -> Produto.builder().codigo(codigo).build())
              .forEach(produto -> pedido.getItens().add(produto));
        return atualizarPedido(pedido, codigoPedido);
    }

    public Pedido removerProduto(String codigoPedido, String codigoProduto) throws Exception {
        var pedido = pedidoPort.consultarPedidoPorCodigo(codigoPedido);
          pedido.getItens().stream()
            .filter(produto -> StringUtils.equals(codigoProduto, produto.getCodigo()))
            .findFirst()
            .ifPresent(
              produto -> pedido.getItens().remove(produto)
            );
        return atualizarPedido(pedido, codigoPedido);
    }
}
