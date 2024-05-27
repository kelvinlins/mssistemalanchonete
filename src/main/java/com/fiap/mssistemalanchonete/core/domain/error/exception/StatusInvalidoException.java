package com.fiap.mssistemalanchonete.core.domain.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StatusInvalidoException extends ResponseStatusException {
  public StatusInvalidoException() {
    super(HttpStatus.NOT_ACCEPTABLE, "O status atual do pedido não permite essa ação");
  }
}
