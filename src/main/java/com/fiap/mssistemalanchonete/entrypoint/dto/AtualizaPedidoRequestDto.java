package com.fiap.mssistemalanchonete.entrypoint.dto;

import com.fiap.mssistemalanchonete.core.enums.StatusPedidoEnum;

public record AtualizaPedidoRequestDto(
  StatusPedidoEnum status
) {
}
