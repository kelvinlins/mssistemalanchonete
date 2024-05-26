package com.fiap.mssistemalanchonete.adapter.in.controller.dto;

import java.util.List;

public record AcompanhamentoCozinhaResponseDto(
  String codigo,
  List<ComboAcompanhamentoCozinhaDto> combos,
  String status,
  String tempoEspera
) {
}
