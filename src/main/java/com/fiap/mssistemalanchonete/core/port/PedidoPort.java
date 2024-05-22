package com.fiap.mssistemalanchonete.core.port;

import com.fiap.mssistemalanchonete.core.domain.model.Pedido;

import java.util.List;

public interface PedidoPort {

    Pedido salvarPedido(Pedido pedido);
    Pedido atualizarPedido(Pedido pedido);
    Pedido consultarPedidoPorCodigo(String codigo);
    void deletarPedido(Pedido pedido);
    List<Pedido> listarPedidos();
}
