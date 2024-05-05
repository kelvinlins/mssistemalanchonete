package com.fiap.mssistemalanchonete.adapter.driver;

import com.fiap.mssistemalanchonete.core.applications.userCase.IClienteUserCase;
import com.fiap.mssistemalanchonete.core.domain.cliente.Cliente;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
public class ClienteController {

    @Autowired
    private IClienteUserCase service;

    @Operation(
            description = "Realiza o cadastro do cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!")
            }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createClient(@RequestBody final Cliente cliente) throws Exception {
        return ResponseEntity.accepted().body(service.setCliente(cliente));
    }

    @Operation(
            description = "Retorna os dados do cliente peko CPF",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso!")
            }
    )
    @GetMapping(value = "/{cpf}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> getClientByCpf(@PathVariable final String cpf) throws Exception {
        return ResponseEntity.accepted().body(service.getCliente(cpf));
    }

    @Operation(
            description = "Retorna lista de clientes cadastrados no sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso!")
            }
    )
    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> getAllClients(
            @RequestHeader(value = "Authorization", required = true) String token,
            @RequestHeader(value = "Content-Type", required = true) String contentType,
            @PathVariable final String cpf) throws Exception {
        return ResponseEntity.accepted().body(service.getCliente(cpf));
    }

}
