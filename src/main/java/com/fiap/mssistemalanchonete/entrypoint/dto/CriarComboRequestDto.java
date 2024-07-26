package com.fiap.mssistemalanchonete.entrypoint.dto;

import java.util.List;

public record CriarComboRequestDto (
  List<AdicionarProdutoRequestDto> itens
){}
