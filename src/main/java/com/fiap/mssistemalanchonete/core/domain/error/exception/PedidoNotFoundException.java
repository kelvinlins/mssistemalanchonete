package com.fiap.mssistemalanchonete.core.domain.error.exception;

public class PedidoNotFoundException extends NotFoundException{
  public PedidoNotFoundException() {
    super("Pedido Não Encontrado!");
  }
}
