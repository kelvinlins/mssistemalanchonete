package com.fiap.mssistemalanchonete.core.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PedidoPagoException extends ResponseStatusException {
  public PedidoPagoException() {
    super(HttpStatus.NOT_ACCEPTABLE, "Pedido ja foi pago e realizado o checkout!");
  }
}
