package com.fiap.mssistemalanchonete.core.usecase;

import com.fiap.mssistemalanchonete.core.model.Pedido;
import com.fiap.mssistemalanchonete.core.model.StatusPedidoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoUseCaseFacade {

    Pedido criarPedido(Pedido pedido) throws Exception;

    Pedido atualizarPedido(Pedido pedido, String codigo) throws Exception;

    void deletarPedido(String codigo) throws Exception;

    Pedido adicionarProdutos(String codigoPedido, Integer codigoCombo, String codigoProduto, Integer quantidade) throws Exception;

    Pedido removerProduto(String codigoPedido, Integer comboId, String codigoProduto, Integer quantidade) throws Exception;

    Page<Pedido> listarPedidos(Pageable pageable, List<StatusPedidoEnum> status) throws Exception;

    Page<Pedido> listarPedidosOrdenadamente(Pageable pageable) throws Exception;

    Integer adicionarCombo(String codigoPedido, Pedido pedidoParaAtualizar) throws Exception;

    Pedido getPedidoPorCodigo(String codigoPedido);

    void deletarCombo(String codigoPedido, Integer comboId) throws Exception;

    Pedido checkout(String codigoPedido) throws Exception;

}
