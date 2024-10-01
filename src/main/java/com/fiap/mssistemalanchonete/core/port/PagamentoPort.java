package com.fiap.mssistemalanchonete.core.port;

import com.fiap.mssistemalanchonete.core.model.Pagamento;

public interface PagamentoPort {
    Pagamento realizarPagamento(String codigoPedido);
}
