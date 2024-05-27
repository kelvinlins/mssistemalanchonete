package com.fiap.mssistemalanchonete.core.domain.error.exception;

public class ComboNotFoundException extends NotFoundException{
  public ComboNotFoundException() {
    super("Combo não encontrado!");
  }
}
