package com.fiap.mssistemalanchonete.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Combo {
  private Map<Produto, Integer> itens = new HashMap<>();
  private Integer id = 1;

  public Boolean semProdutos() {
    if (Objects.isNull(itens) || itens.isEmpty()){
      return Boolean.TRUE;
    }

    var quantidadePositivos = itens.values().stream()
      .filter(Objects::nonNull)
      .filter(integer -> integer>0)
      .count();

    return quantidadePositivos<1;
  }

  public BigDecimal getSubTotal() {
    if (Objects.isNull(itens)){
      return BigDecimal.ZERO;
    }
    List<BigDecimal> subTotais = new ArrayList<>();
    itens.forEach(
      (produto, quantidade) -> subTotais.add(
        produto.getPreco().multiply(BigDecimal.valueOf(quantidade.longValue())))
    );
    return subTotais.stream().reduce(BigDecimal.ZERO, (BigDecimal::add));
  }
}
