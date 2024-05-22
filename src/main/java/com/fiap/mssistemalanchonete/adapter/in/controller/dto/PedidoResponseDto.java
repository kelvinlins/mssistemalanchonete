package com.fiap.mssistemalanchonete.adapter.in.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PedidoResponseDto(
  String codigo,
  String status
) {
}
