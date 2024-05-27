package com.fiap.mssistemalanchonete.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensCombo {
  private Produto produto;
  private Integer quantidade;
}
