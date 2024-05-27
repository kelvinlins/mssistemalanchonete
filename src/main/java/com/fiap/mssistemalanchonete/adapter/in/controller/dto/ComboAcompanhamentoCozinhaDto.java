package com.fiap.mssistemalanchonete.adapter.in.controller.dto;

import java.util.List;

public record ComboAcompanhamentoCozinhaDto(
  Integer id,
  List<ItemComboAcompanhamentoCozinhaDto> itens
){
}
