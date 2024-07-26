package com.fiap.mssistemalanchonete.core.port;

import com.fiap.mssistemalanchonete.core.model.Cliente;

import java.util.List;

public interface ClientePort {
    Cliente salvarCliente(Cliente cliente);
    List<Cliente> consultarTodosClientes();
    Cliente consultarClientePorCpf(String cpf);
    Cliente consultarClientePorCodigo(Long codigo);
    Cliente atualizarCliente(Cliente clienteAntigo, Cliente clienteNovo);
    void deletarCliente(Cliente cliente);
}
