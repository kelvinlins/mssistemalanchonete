package com.fiap.mssistemalanchonete.core.port;

import com.fiap.mssistemalanchonete.core.domain.model.Cliente;

import java.util.List;

public interface ClientePort {
    Cliente salvarCliente(Cliente cliente);
    List<Cliente> consultarTodosClientes();
    Cliente consultarClientePorCpf(String cpf);
}
