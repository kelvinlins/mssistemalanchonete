package com.fiap.mssistemalanchonete.adapter.in.controller.dto;

import com.fiap.mssistemalanchonete.core.domain.model.Produto;

import java.util.HashMap;
import java.util.Map;

public record AdicionarProdutoRequestDto(
  String codigoProduto,
  Integer quantidade
) {
  public Map<Produto, Integer> toMapItem() {
    Map<Produto, Integer> map = new HashMap<>();
    map.put(
      Produto.builder()
        .codigo(codigoProduto())
        .build(),
      quantidade()
    );
    return null;
  }
}
