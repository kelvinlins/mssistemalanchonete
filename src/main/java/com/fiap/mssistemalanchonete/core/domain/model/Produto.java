package com.fiap.mssistemalanchonete.core.domain.model;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private String codigo;
    private String nome;
    private String descricao;
    private String preco;
    private String categoria;
}
