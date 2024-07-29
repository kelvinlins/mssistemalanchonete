package com.fiap.mssistemalanchonete.dataprovider.service;

import com.fiap.mssistemalanchonete.core.model.Pagamento;
import com.fiap.mssistemalanchonete.core.port.PagamentoPort;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService implements PagamentoPort {
    @Override
    public Pagamento realizarPagamento(String codigoPedido) {
        return Pagamento.builder() //retorno mockado sem integração
                .aprovado(Boolean.TRUE)
                .build();
    }
}
