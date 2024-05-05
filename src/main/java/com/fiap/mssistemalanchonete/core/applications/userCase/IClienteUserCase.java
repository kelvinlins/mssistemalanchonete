package com.fiap.mssistemalanchonete.core.applications.userCase;

import com.fiap.mssistemalanchonete.core.domain.cliente.Cliente;
import org.springframework.stereotype.Service;

@Service
public interface IClienteUserCase {

    Cliente setCliente(Cliente cliente);

    Cliente getCliente(String cliente);

}
