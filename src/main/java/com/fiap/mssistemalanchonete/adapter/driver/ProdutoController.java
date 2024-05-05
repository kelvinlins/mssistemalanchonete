package com.fiap.mssistemalanchonete.adapter.driver;

import com.fiap.mssistemalanchonete.core.applications.userCase.IProdutoUserCase;
import com.fiap.mssistemalanchonete.core.domain.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProdutoController {

    @Autowired
    private IProdutoUserCase service;

    @PostMapping(value = "/produto", consumes = {"*"})
    public ResponseEntity<Object> crateControllerValidation(
            @RequestHeader(value = "Authorization", required = true) String token,
            @RequestHeader(value = "Content-Type", required = true) String contentType,
            @RequestBody final Produto produto) throws Exception {
        return ResponseEntity.accepted().body(service.setProduto(produto));
    }

    @GetMapping(value = "/produto/{categoria}", consumes = {"*"})
    public ResponseEntity<Object> crateControllerValidation(
            @RequestHeader(value = "Authorization", required = true) String token,
            @RequestHeader(value = "Content-Type", required = true) String contentType,
            @PathVariable final String categoria) throws Exception {
        return ResponseEntity.accepted().body(service.getProdutoPorCategoria(categoria));
    }

}
