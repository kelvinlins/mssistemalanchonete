package com.fiap.mssistemalanchonete.adapter.in.controller;

import com.fiap.mssistemalanchonete.core.domain.model.Cliente;

import com.fiap.mssistemalanchonete.core.useCase.cliente.ClienteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteUseCase clienteUserCase;

    @Autowired
    public ClienteController(ClienteUseCase clienteUserCase){
        this.clienteUserCase = clienteUserCase;
    }

    @Operation(
            description = "Retorna todos os clientes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso!")
            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Cliente>> consultarTodosClientes() {
        return ResponseEntity.ok(clienteUserCase.consultarTodosClientes());
    }

    @Operation(
            description = "Realiza o cadastro do cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!")
            }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid final Cliente cliente) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201))
                .body(clienteUserCase.salvarCliente(cliente));
    }

    @Operation(
            description = "Retorna os dados do cliente peko CPF",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso!")
            }
    )
    @GetMapping(value = "/{cpf}", produces = "application/json")
    public ResponseEntity<Cliente> consultarClientePorCpf(@PathVariable final String cpf) {
        return ResponseEntity.ok(clienteUserCase.consultarClientePorCpf(cpf));
    }

    @Operation(
            description = "Atualiza um cliente atraves do codigo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!")
            }
    )
    @PatchMapping(value = "/{codigo}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cliente> atualizarClientePorCodigo(
            @RequestBody @Valid final Cliente produto,
            @PathVariable final Long codigo) {
        return ResponseEntity.ok(clienteUserCase.atualizarCliente(produto, codigo));
    }
    @Operation(
            description = "Remove um cliente cadastrado pelo codigo",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso!")
            }
    )
    @DeleteMapping(value = "/{codigo}", produces = "application/json")
    public ResponseEntity<Object> removerClientePorCodigo(@PathVariable final Long codigo) {
        clienteUserCase.deletarCliente(codigo);
        return ResponseEntity.noContent().build();
    }
}
