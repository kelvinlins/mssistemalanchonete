package com.fiap.mssistemalanchonete.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="cliente")
public class ClienteEntity {

    @Id
    @Column(name="cpf")
    private String codigoCpf;

    @Column(name="nome")
    private String nome;

    @Column(name="email")
    private String email;
}
