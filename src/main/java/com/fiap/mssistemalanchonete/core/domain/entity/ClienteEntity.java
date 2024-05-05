package com.fiap.mssistemalanchonete.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
