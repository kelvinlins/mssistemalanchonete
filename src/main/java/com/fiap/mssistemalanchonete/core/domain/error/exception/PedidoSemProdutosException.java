package com.fiap.mssistemalanchonete.core.domain.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PedidoSemProdutosException extends ResponseStatusException {
  public PedidoSemProdutosException() {
    super(HttpStatus.NOT_ACCEPTABLE, "O Pedido precisa ter ao menos um produto para essa ação!");
  }
}
