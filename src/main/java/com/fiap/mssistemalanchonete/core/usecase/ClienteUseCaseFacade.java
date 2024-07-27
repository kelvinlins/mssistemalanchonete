package com.fiap.mssistemalanchonete.core.usecase;

import com.fiap.mssistemalanchonete.core.model.Cliente;

import java.util.List;

public interface ClienteUseCaseFacade {

    Cliente salvarCliente(Cliente cliente);

    Cliente atualizarCliente(Cliente cliente, Long codigo);

    void deletarCliente(Long codigo);

    Cliente consultarClientePorCpf(String cpf);

    Cliente consultarClientePorCodigo(Long codigo);

    List<Cliente> consultarTodosClientes();

}
