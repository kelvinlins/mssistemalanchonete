package com.fiap.mssistemalanchonete.core.applications.userCase;

import com.fiap.mssistemalanchonete.adapter.driven.infra.repositories.ClienteRepository;
import com.fiap.mssistemalanchonete.core.domain.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteUserCase implements IClienteUserCase {

    @Autowired
    ClienteRepository repository;

    @Override
    public Cliente setCliente(Cliente cliente) {
        return repository.registraCliente(cliente);
    }

    @Override
    public Cliente getCliente(String cpf) {
        return repository.retornaClientePorCPF(cpf);
    }

}
