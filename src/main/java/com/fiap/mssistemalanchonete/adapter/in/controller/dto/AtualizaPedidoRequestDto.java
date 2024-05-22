package com.fiap.mssistemalanchonete.adapter.in.controller.dto;

import java.util.List;

public record AtualizaPedidoRequestDto(
  List<String> codigoItens,
  String status
) {
}
