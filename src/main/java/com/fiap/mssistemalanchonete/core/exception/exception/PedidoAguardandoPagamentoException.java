package com.fiap.mssistemalanchonete.core.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PedidoAguardandoPagamentoException extends ResponseStatusException {
  public PedidoAguardandoPagamentoException() {
    super(HttpStatus.NOT_ACCEPTABLE, "È necessário que o pedido seja pago para realizar essa ação!");
  }
}
