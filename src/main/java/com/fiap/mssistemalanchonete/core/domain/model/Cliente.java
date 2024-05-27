package com.fiap.mssistemalanchonete.core.domain.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cliente {
    private String codigo;
    private String cpf;
    private String nome;
    private String email;
}