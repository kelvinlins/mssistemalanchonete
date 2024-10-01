package com.fiap.mssistemalanchonete.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ComboDto (
  Integer id,
  List<ItemComboDto> itens,
  BigDecimal subtotal
) {
}
