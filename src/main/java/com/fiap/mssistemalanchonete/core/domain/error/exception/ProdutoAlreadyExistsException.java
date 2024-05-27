package com.fiap.mssistemalanchonete.core.domain.error.exception;

public class ProdutoAlreadyExistsException extends AlreadyExistsException{
    public ProdutoAlreadyExistsException(String codigo) {
        super("Produto " + codigo + " ja possui cadastro!");
    }
}
