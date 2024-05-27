package com.fiap.mssistemalanchonete.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="produto")
public class ProdutoEntity {

    @Id
    @Column(name="codigo")
    private String codigo;

    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @Column(name="preco")
    private String preco;

    @Column(name="categoria")
    private String categoria;
}
