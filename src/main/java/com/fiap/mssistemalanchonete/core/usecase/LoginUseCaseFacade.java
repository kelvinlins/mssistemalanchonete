package com.fiap.mssistemalanchonete.core.usecase;

import com.fiap.mssistemalanchonete.core.model.Login;
import com.fiap.mssistemalanchonete.core.model.Token;

public interface LoginUseCaseFacade {
    Token fazerLogin(Login login);
}
