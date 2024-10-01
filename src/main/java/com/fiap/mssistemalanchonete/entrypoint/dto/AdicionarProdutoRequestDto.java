package com.fiap.mssistemalanchonete.entrypoint.dto;

import com.fiap.mssistemalanchonete.core.model.Produto;

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
