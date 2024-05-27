package com.fiap.mssistemalanchonete.core.domain.error.exception;

public class ClienteNotFoundException extends NotFoundException{
  public ClienteNotFoundException(String codigo) {
    super("Cliente " + codigo + " não encontrado!");
  }
}
