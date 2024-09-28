package com.fiap.mssistemalanchonete.entrypoint.dto;

import java.util.List;

public record ComboAcompanhamentoCozinhaDto(
  Integer id,
  List<ItemComboAcompanhamentoCozinhaDto> itens
){
}
