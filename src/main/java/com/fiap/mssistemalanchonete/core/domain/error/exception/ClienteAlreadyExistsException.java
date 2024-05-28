package com.fiap.mssistemalanchonete.core.domain.error.exception;

public class ClienteAlreadyExistsException extends AlreadyExistsException{
    public ClienteAlreadyExistsException(String codigo) {
        super("Cliente " + codigo + " ja possui cadastro!");
    }
}
