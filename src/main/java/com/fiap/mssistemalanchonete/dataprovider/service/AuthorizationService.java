package com.fiap.mssistemalanchonete.dataprovider.service;

import com.fiap.mssistemalanchonete.dataprovider.repository.ClienteRepository;
import com.fiap.mssistemalanchonete.dataprovider.repository.jpa.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    IClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return clienteRepository.findByCpf(cpf);
    }
}