package com.fiap.mssistemalanchonete.adapter.in.controller;

import com.fiap.mssistemalanchonete.core.domain.model.Cliente;

import com.fiap.mssistemalanchonete.core.userCase.cliente.ClienteUserCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteUserCase clienteUserCase;

    @Autowired
    public ClienteController(ClienteUserCase clienteUserCase){
        this.clienteUserCase = clienteUserCase;
    }

    @Operation(
            description = "Realiza o cadastro do cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!")
            }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody final Cliente cliente) throws Exception {
        return ResponseEntity.status(HttpStatusCode.valueOf(201))
                .body(clienteUserCase.salvarCliente(cliente));
    }

    @Operation(
            description = "Retorna os dados do cliente peko CPF",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso!")
            }
    )
    @GetMapping(value = "/{cpf}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cliente> consultarClientePorCpf(@PathVariable final String cpf) throws Exception {
        return ResponseEntity.ok(clienteUserCase.consultarClientePorCpf(cpf));
    }

    @Operation(
            description = "Retorna lista de clientes cadastrados no sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso!")
            }
    )
    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Cliente>> consultarTodosClientes() {
        return ResponseEntity.ok(clienteUserCase.consultarTodosClientes());
    }

}
