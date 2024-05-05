package com.fiap.mssistemalanchonete.core.domain.produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Produto {

    private String code;
    private String nome;
    private String descricao;
    private String preco;
    private String categoria;

}
