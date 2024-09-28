package com.fiap.mssistemalanchonete.entrypoint.dto;

public record ItemComboAcompanhamentoCozinhaDto(
  String codigo,
  String nome,
  String descricao,
  String categoria,
  Integer quantidade
) {
}
