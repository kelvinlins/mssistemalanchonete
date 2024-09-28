package com.fiap.mssistemalanchonete.core.usecase;

import com.fiap.mssistemalanchonete.core.model.Pagamento;

public interface PagamentoUseCaseFacade {
    Pagamento realizarPagamento(String codigoPedido);
}
