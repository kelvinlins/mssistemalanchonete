package com.fiap.mssistemalanchonete.core.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private String codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
}
