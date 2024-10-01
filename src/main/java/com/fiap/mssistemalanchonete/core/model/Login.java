package com.fiap.mssistemalanchonete.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @CPF(message = "CPF Inválido!")
    private String cpf;
    private String senha;
}