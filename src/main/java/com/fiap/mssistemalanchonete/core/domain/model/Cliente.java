package com.fiap.mssistemalanchonete.core.domain.model;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cliente {
    private Long codigo;
    @CPF(message = "CPF Inválido!")
    private String cpf;
    private String nome;
    @Email(message = "Email inválido!")
    private String email;
}
