package com.fiap.mssistemalanchonete.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    private String senha;

    public String getCodigoAsString(){
        if (Objects.isNull(codigo)){
            return null;
        }
        return String.valueOf(codigo);
    }
}
