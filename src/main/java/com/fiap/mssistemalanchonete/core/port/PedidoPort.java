package com.fiap.mssistemalanchonete.core.port;

import com.fiap.mssistemalanchonete.core.model.Pedido;
import com.fiap.mssistemalanchonete.core.model.StatusPedidoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PedidoPort {

    Pedido salvarPedido(Pedido pedido);
    Pedido atualizarPedido(Pedido pedido);
    Optional<Pedido> consultarPedidoPorCodigo(String codigo);
    void deletarPedido(Pedido pedido);
    Page<Pedido> listarPedidos(Pageable pageable, List<StatusPedidoEnum> statusList);
}
