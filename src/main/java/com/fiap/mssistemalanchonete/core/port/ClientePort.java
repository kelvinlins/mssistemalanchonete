package com.fiap.mssistemalanchonete.core.port;

import com.fiap.mssistemalanchonete.core.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClientePort {
    Cliente salvarCliente(Cliente cliente);
    List<Cliente> consultarTodosClientes();
    Cliente consultarClientePorCpf(String cpf);

  Optional<Cliente> consultarClientePorCodigo(String codigo);
}
