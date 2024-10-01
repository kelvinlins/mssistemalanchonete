package com.fiap.mssistemalanchonete.entrypoint.dto;

import java.util.List;

public record AcompanhamentoCozinhaResponseDto(
  String codigo,
  List<ComboAcompanhamentoCozinhaDto> combos,
  String status,
  String tempoEspera
) {
}
