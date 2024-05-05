package com.fiap.mssistemalanchonete.adapter.driven.infra.repositories;

import com.fiap.mssistemalanchonete.core.domain.cliente.Cliente;
import com.fiap.mssistemalanchonete.core.domain.entity.ClienteEntity;
import com.fiap.mssistemalanchonete.core.domain.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteRepository {

    @Autowired
    IClienteRepository repository;

    public Cliente registraCliente(Cliente cliente) {
        ClienteEntity cliente1 = ClienteEntity.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .build();

        repository.save(cliente1);
        return cliente;

    }

    public Cliente retornaClientePorCPF(String cpf){
        Optional<ClienteEntity> cliente = repository.findById(cpf);
        Cliente cliente1 = Cliente.builder()
                .nome(cliente.get().getNome())
                .cpf(cliente.get().getCpf())
                .email(cliente.get().getEmail())
                .build();
        return cliente1;
    }

}
