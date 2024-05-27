package com.fiap.mssistemalanchonete.core.userCase.cliente;


import com.fiap.mssistemalanchonete.core.domain.error.exception.ClienteNotFoundException;
import com.fiap.mssistemalanchonete.core.domain.model.Cliente;
import com.fiap.mssistemalanchonete.core.port.ClientePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteUserCase {

    private final ClientePort clientePort;

    @Autowired
    public ClienteUserCase(ClientePort clientePort){
        this.clientePort = clientePort;
    }

    public Cliente salvarCliente(Cliente cliente) throws Exception {

        if (!ObjectUtils.isEmpty(clientePort.consultarClientePorCpf(cliente.getCpf())))
            throw new Exception();

        return clientePort.salvarCliente(cliente);
    }

    public List<Cliente> consultarTodosClientes() {
        return clientePort.consultarTodosClientes();
    }

    public Cliente consultarClientePorCpf(String cpf) throws Exception {
        Cliente cliente = clientePort.consultarClientePorCpf(cpf);

        if (ObjectUtils.isEmpty(cliente))
            throw new Exception();
        return cliente;
    }

    public Cliente consultarClientePorCodigo(String codigo) {
        return clientePort.consultarClientePorCodigo(codigo)
          .orElseThrow(()-> new ClienteNotFoundException(codigo));
    }
}
