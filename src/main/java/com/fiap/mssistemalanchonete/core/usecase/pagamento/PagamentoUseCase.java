package com.fiap.mssistemalanchonete.core.usecase.pagamento;

import com.fiap.mssistemalanchonete.core.enums.StatusPedidoEnum;
import com.fiap.mssistemalanchonete.core.exception.exception.PedidoNotFoundException;
import com.fiap.mssistemalanchonete.core.exception.exception.PedidoPagoException;
import com.fiap.mssistemalanchonete.core.exception.exception.PedidoSemProdutosException;
import com.fiap.mssistemalanchonete.core.model.Pagamento;
import com.fiap.mssistemalanchonete.core.model.Pedido;
import com.fiap.mssistemalanchonete.core.port.PagamentoPort;
import com.fiap.mssistemalanchonete.core.port.PedidoPort;
import com.fiap.mssistemalanchonete.core.usecase.PagamentoUseCaseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class PagamentoUseCase implements PagamentoUseCaseFacade {

    private final PagamentoPort pagamentoPort;
    private final PedidoPort pedidoPort;

    @Autowired
    public PagamentoUseCase(PagamentoPort pagamentoPort, PedidoPort pedidoPort) {
        this.pagamentoPort = pagamentoPort;
        this.pedidoPort = pedidoPort;
    }

    @Override
    public Pagamento realizarPagamento(String codigoPedido) {
        Pedido pedido = pedidoPort.consultarPedidoPorCodigo(codigoPedido);

        if (ObjectUtils.isEmpty(pedido))
            throw new PedidoNotFoundException();

        if (!ObjectUtils.isEmpty(pedido.getHoraCheckout())){
            throw new PedidoPagoException();
        }

        if (Boolean.TRUE.equals(pedido.semProdutos())){
            throw new PedidoSemProdutosException();
        }

        pedido.setStatus(StatusPedidoEnum.PAGO);
        pedidoPort.atualizarPedido(pedido);
        return pagamentoPort.realizarPagamento(codigoPedido);
    }
}
