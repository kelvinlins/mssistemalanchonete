package com.fiap.mssistemalanchonete.core.domain.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuantidadeInvalidaException extends ResponseStatusException {
  public QuantidadeInvalidaException() {
    super(HttpStatus.BAD_REQUEST, "A quantidade precisa ser um número natural maior que zero!");
  }
}
