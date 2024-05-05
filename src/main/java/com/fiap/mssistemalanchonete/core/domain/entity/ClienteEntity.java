package com.fiap.mssistemalanchonete.core.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="cliente")
public class ClienteEntity {

    @Id
    private String cpf;

    @Column(name="nome")
    private String nome;

    @Column(name="email")
    private String email;

}
