package com.fiap.mssistemalanchonete.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private String codigo;
    private Cliente cliente;
    private List<Combo> combos = new ArrayList<>();
    private BigDecimal desconto;
    private StatusPedidoEnum status;
    private LocalDateTime horaCheckout;

    public String getTempoEspera(){
        if (Objects.isNull(horaCheckout)){
            return "0";
        }
        var tempoEsperada = Duration.between(horaCheckout, LocalDateTime.now());
        return String.valueOf(tempoEsperada.toMinutes())+" min";
    }

    public Boolean semProdutos() {
        return combos.stream()
          .map(Combo::semProdutos)
          .reduce(Boolean.TRUE, (a, b)-> a && b);
    }

    public BigDecimal getTotal() {
        return combos.stream()
          .map(Combo::getSubTotal)
          .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getCodigoClienteAsString(){
        return Objects.nonNull(cliente)?
          cliente.getCodigoAsString() :
          null;
    }
}
