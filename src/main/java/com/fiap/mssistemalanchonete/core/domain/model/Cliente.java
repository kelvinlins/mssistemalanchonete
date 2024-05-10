package com.fiap.mssistemalanchonete.core.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cliente {
    private String cpf;
    private String nome;
    private String email;
}
