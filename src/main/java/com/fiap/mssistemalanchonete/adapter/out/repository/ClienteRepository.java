package com.fiap.mssistemalanchonete.adapter.out.repository;

import com.fiap.mssistemalanchonete.adapter.out.mapper.ClienteMapper;
import com.fiap.mssistemalanchonete.adapter.out.repository.jpa.IClienteRepository;
import com.fiap.mssistemalanchonete.core.domain.model.Cliente;
import com.fiap.mssistemalanchonete.core.port.ClientePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClienteRepository implements ClientePort {

    private final IClienteRepository iClienteRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteRepository(IClienteRepository iClienteRepository, ClienteMapper clienteMapper){
        this.iClienteRepository = iClienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        return clienteMapper.toDomain(iClienteRepository.save(clienteMapper.toEntity(cliente)));
    }

    @Override
    public List<Cliente> consultarTodosClientes() {
        return iClienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Cliente consultarClientePorCpf(String cpf) {
        return iClienteRepository.findById(cpf)
                .map(clienteMapper::toDomain)
                .orElse(null);
    }

    @Override
    public Optional<Cliente> consultarClientePorCodigo(String codigo) {
        return iClienteRepository.findByCodigo(codigo)
          .map(clienteMapper::toDomain);
    }
}
