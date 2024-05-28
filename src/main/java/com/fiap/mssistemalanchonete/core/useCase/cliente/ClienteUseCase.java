package com.fiap.mssistemalanchonete.core.useCase.cliente;


import com.fiap.mssistemalanchonete.core.domain.error.exception.ClienteAlreadyExistsException;
import com.fiap.mssistemalanchonete.core.domain.error.exception.ClienteNotFoundException;
import com.fiap.mssistemalanchonete.core.domain.model.Cliente;
import com.fiap.mssistemalanchonete.core.port.ClientePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteUseCase {

    private final ClientePort clientePort;

    @Autowired
    public ClienteUseCase(ClientePort clientePort){
        this.clientePort = clientePort;
    }

    public Cliente salvarCliente(Cliente cliente) {

        if (!ObjectUtils.isEmpty(cliente.getCpf()) && !ObjectUtils.isEmpty(clientePort.consultarClientePorCpf(cliente.getCpf())))
            throw new ClienteAlreadyExistsException(cliente.getCpf());

        return clientePort.salvarCliente(cliente);
    }

    public List<Cliente> consultarTodosClientes() {
        return clientePort.consultarTodosClientes();
    }

    public Cliente consultarClientePorCpf(String cpf) {
        Cliente cliente = clientePort.consultarClientePorCpf(cpf);

        if (ObjectUtils.isEmpty(cliente))
            throw new ClienteNotFoundException(cpf);

        return cliente;
    }

    public Cliente atualizarCliente(Cliente cliente, Long codigo) {

        Cliente clienteAntigo = clientePort.consultarClientePorCodigo(codigo);

        if (ObjectUtils.isEmpty(clienteAntigo))
            throw new ClienteNotFoundException(String.valueOf(codigo));

        return clientePort.atualizarCliente(clienteAntigo, cliente);
    }

    public void deletarCliente(Long codigo) {

        Cliente cliente = clientePort.consultarClientePorCodigo(codigo);

        if (ObjectUtils.isEmpty(cliente))
            throw new ClienteNotFoundException(String.valueOf(codigo));

        clientePort.deletarCliente(cliente);
    }

    public Cliente consultarClientePorCodigo(Long codigo) {
        return Optional.ofNullable(clientePort.consultarClientePorCodigo(codigo))
                .orElseThrow(()-> new ClienteNotFoundException(String.valueOf(codigo)));
    }
}
