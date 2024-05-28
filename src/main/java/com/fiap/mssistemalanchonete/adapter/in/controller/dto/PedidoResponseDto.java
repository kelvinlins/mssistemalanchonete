package com.fiap.mssistemalanchonete.adapter.in.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PedidoResponseDto(
  String codigo,
  String status,
  List<ComboDto> combos,
  BigDecimal total,
  LocalDateTime horaCheckout,
  String tempoEspera,
  String cliente
) {
}
