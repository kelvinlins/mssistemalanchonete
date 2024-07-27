package com.fiap.mssistemalanchonete.dataprovider.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name="cpf", unique = true)
    private String cpf;

    @Column(name="nome")
    private String nome;

    @Column(name="email")
    private String email;
}
