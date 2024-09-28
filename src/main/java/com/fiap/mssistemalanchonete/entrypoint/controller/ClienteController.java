package com.fiap.mssistemalanchonete.entrypoint.controller;

import com.fiap.mssistemalanchonete.core.exception.ErrorResponse;
import com.fiap.mssistemalanchonete.core.model.Cliente;

import com.fiap.mssistemalanchonete.core.usecase.ClienteUseCaseFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteUseCaseFacade clienteUseCaseFacade;

    @Operation(
      description = "Retorna todos os clientes",
      responses = {
        @ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso!")
      }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Cliente>> consultarTodosClientes() {
        return ResponseEntity.ok(clienteUseCaseFacade.consultarTodosClientes());
    }

    @Operation(
      description = "Realiza o cadastro do cliente",
      responses = {
        @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!"),
        @ApiResponse(responseCode = "400",
          description = "Cliente já posssui cadastro!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid final Cliente cliente) {
        return ResponseEntity.ok(clienteUseCaseFacade.salvarCliente(cliente));
    }

    @Operation(
      description = "Retorna os dados do cliente peko CPF",
      responses = {
        @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso!"),
        @ApiResponse(responseCode = "404",
          description = "Cliente não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @GetMapping(value = "/{cpf}", produces = "application/json")
    public ResponseEntity<Cliente> consultarClientePorCpf(@PathVariable final String cpf) {
        return ResponseEntity.ok(clienteUseCaseFacade.consultarClientePorCpf(cpf));
    }

    @Operation(
      description = "Atualiza um cliente atraves do codigo",
      responses = {
        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!"),
        @ApiResponse(responseCode = "404",
          description = "Cliente não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @PatchMapping(value = "/{codigo}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cliente> atualizarClientePorCodigo(
      @RequestBody @Valid final Cliente produto,
      @PathVariable final Long codigo) {
        return ResponseEntity.ok(clienteUseCaseFacade.atualizarCliente(produto, codigo));
    }
    @Operation(
      description = "Remove um cliente cadastrado pelo codigo",
      responses = {
        @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso!"),
        @ApiResponse(responseCode = "404",
          description = "Cliente não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @DeleteMapping(value = "/{codigo}", produces = "application/json")
    public ResponseEntity<Object> removerClientePorCodigo(@PathVariable final Long codigo) {
        clienteUseCaseFacade.deletarCliente(codigo);
        return ResponseEntity.noContent().build();
    }
}
