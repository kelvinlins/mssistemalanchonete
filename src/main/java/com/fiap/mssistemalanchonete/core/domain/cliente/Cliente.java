package com.fiap.mssistemalanchonete.core.domain.cliente;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Cliente {

    private String cpf;
    private String nome;
    private String email;

}
