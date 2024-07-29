package com.fiap.mssistemalanchonete.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum StatusPedidoEnum {
  AGUARDANDO_PAGAMENTO(Boolean.FALSE),
  RECEBIDO(Boolean.TRUE),
  EM_PREPARACAO(Boolean.TRUE),
  PRONTO(Boolean.TRUE),
  FINALIZADO(Boolean.FALSE),
  PAGO(Boolean.FALSE);

  private final Boolean acompanhar;

  public static List<StatusPedidoEnum> getStatusAcompanhar() {
    return Arrays.stream(StatusPedidoEnum.values())
      .filter(statusPedidoEnum -> Boolean.TRUE.equals(statusPedidoEnum.getAcompanhar()))
      .toList();
  }
}
