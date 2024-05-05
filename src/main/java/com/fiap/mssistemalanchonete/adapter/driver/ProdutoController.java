package com.fiap.mssistemalanchonete.adapter.driver;

import com.fiap.mssistemalanchonete.core.applications.userCase.IProdutoUserCase;
import com.fiap.mssistemalanchonete.core.domain.produto.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProdutoController {

    @Autowired
    private IProdutoUserCase service;

    @Operation(
            description = "Cria novo produto",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso!")
            }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createProduct(
            @RequestBody final Produto produto) throws Exception {
        return ResponseEntity.status(201).body(service.setProduto(produto));
    }

    @Operation(
            description = "Retorna produtos pela categoria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso!")
            }
    )
    @GetMapping(value = "/{category}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> crateControllerValidation(@PathVariable final String categoria) throws Exception {
        return ResponseEntity.accepted().body(service.getProdutoPorCategoria(categoria));
    }
}
