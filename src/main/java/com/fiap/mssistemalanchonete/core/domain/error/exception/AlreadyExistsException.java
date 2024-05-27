package com.fiap.mssistemalanchonete.core.domain.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlreadyExistsException extends ResponseStatusException {
    public AlreadyExistsException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
