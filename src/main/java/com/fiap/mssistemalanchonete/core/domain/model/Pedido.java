package com.fiap.mssistemalanchonete.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private String codigo;
    private Cliente cliente;
    private List<Produto> itens = new ArrayList<>();
    private BigDecimal desconto;
    private StatusPedidoEnum status;
    private LocalDateTime horaCheckout;
}
