package com.fiap.mssistemalanchonete.adapter.in.controller.dto;

import java.util.List;

public record CriarComboRequestDto (
  List<AdicionarProdutoRequestDto> itens
){}
