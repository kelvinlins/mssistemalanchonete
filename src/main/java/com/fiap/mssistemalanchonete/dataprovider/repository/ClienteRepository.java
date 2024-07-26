package com.fiap.mssistemalanchonete.dataprovider.repository;

import com.fiap.mssistemalanchonete.dataprovider.entity.ClienteEntity;
import com.fiap.mssistemalanchonete.dataprovider.mapper.ClienteMapper;
import com.fiap.mssistemalanchonete.dataprovider.repository.jpa.IClienteRepository;
import com.fiap.mssistemalanchonete.core.model.Cliente;
import com.fiap.mssistemalanchonete.core.port.ClientePort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        return clienteMapper.toDomain(iClienteRepository.save(clienteMapper.toEntity(cliente)));
    }

    @Override
    public List<Cliente> consultarTodosClientes() {
        return iClienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDomain)
                .toList();
    }

    @Override
    public Cliente consultarClientePorCpf(String cpf) {
        return iClienteRepository.findByCpf(cpf)
                .map(clienteMapper::toDomain)
                .orElse(null);
    }

    @Override
    public Cliente consultarClientePorCodigo(Long codigo) {
        return iClienteRepository.findById(codigo)
                .map(clienteMapper::toDomain)
                .orElse(null);
    }

    @Override
    public Cliente atualizarCliente(Cliente clienteAntigo, Cliente clienteNovo) {
        ClienteEntity clienteEntity = clienteMapper.toEntity(clienteAntigo);
        clienteMapper.merge(clienteNovo, clienteEntity);
        return clienteMapper.toDomain(iClienteRepository.save(clienteEntity));
    }

    @Override
    public void deletarCliente(Cliente cliente) {
        iClienteRepository.delete(clienteMapper.toEntity(cliente));
    }
}
