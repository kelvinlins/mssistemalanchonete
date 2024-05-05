package com.fiap.mssistemalanchonete.adapter.driver;

import com.fiap.mssistemalanchonete.core.applications.userCase.IClienteUserCase;
import com.fiap.mssistemalanchonete.core.domain.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {

    @Autowired
    private IClienteUserCase service;

    @PostMapping(value = "/cliente", consumes = {"*"})
    public ResponseEntity<Object> crateControllerValidation(
            @RequestHeader(value = "Authorization", required = true) String token,
            @RequestHeader(value = "Content-Type", required = true) String contentType,
            @RequestBody final Cliente cliente) throws Exception {
        return ResponseEntity.accepted().body(service.setCliente(cliente));
    }

    @GetMapping(value = "/cliente/{cpf}", consumes = {"*"})
    public ResponseEntity<Object> crateControllerValidation(
            @RequestHeader(value = "Authorization", required = true) String token,
            @RequestHeader(value = "Content-Type", required = true) String contentType,
            @PathVariable final String cpf) throws Exception {
        return ResponseEntity.accepted().body(service.getCliente(cpf));
    }

}
