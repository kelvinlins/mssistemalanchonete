package com.fiap.mssistemalanchonete.adapter.in.controller.dto;

import com.fiap.mssistemalanchonete.core.domain.model.StatusPedidoEnum;

public record AtualizaPedidoRequestDto(
  StatusPedidoEnum status
) {
}
