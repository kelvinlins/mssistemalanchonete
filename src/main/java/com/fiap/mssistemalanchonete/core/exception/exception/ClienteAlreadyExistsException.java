package com.fiap.mssistemalanchonete.core.exception.exception;

public class ClienteAlreadyExistsException extends AlreadyExistsException{
    public ClienteAlreadyExistsException(String codigo) {
        super("Cliente " + codigo + " ja possui cadastro!");
    }
}
