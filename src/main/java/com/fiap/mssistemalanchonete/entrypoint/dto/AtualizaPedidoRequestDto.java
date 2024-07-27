package com.fiap.mssistemalanchonete.entrypoint.dto;

import com.fiap.mssistemalanchonete.core.model.StatusPedidoEnum;

public record AtualizaPedidoRequestDto(
  StatusPedidoEnum status
) {
}
