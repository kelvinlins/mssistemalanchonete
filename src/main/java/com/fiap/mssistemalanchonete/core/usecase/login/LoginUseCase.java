package com.fiap.mssistemalanchonete.core.usecase.login;

import com.fiap.mssistemalanchonete.core.model.Login;
import com.fiap.mssistemalanchonete.core.model.Token;
import com.fiap.mssistemalanchonete.core.port.TokenPort;
import com.fiap.mssistemalanchonete.core.usecase.LoginUseCaseFacade;
import com.fiap.mssistemalanchonete.dataprovider.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase implements LoginUseCaseFacade {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenPort tokenPort;

    @Override
    public Token fazerLogin(Login login) {
        var auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getCpf(), login.getSenha()));
        String jwt = tokenPort.generateToken(((ClienteEntity) auth.getPrincipal()).getUsername());
        return new Token(jwt);
    }
}