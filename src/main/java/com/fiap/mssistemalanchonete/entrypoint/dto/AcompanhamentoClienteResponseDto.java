package com.fiap.mssistemalanchonete.entrypoint.dto;

public record AcompanhamentoClienteResponseDto(
  String codigo,
  String status,
  String tempoEspera
) {
}
