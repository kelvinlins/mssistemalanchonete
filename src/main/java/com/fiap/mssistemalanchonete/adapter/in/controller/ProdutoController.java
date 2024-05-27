package com.fiap.mssistemalanchonete.adapter.in.controller;

import com.fiap.mssistemalanchonete.core.domain.model.Produto;
import com.fiap.mssistemalanchonete.core.userCase.produto.ProdutoUserCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoUserCase produtoUserCase;

    @Autowired
    public ProdutoController(ProdutoUserCase produtoUserCase){
        this.produtoUserCase = produtoUserCase;
    }

    @Operation(
            description = "Cria novo produto",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso!")
            }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Produto> cadastrarProduto(
            @RequestBody final Produto produto) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201))
                .body(produtoUserCase.salvarProduto(produto));
    }

    @Operation(
            description = "Atualiza um produto existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso!")
            }
    )
    @PatchMapping(value = "/{codigo}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Produto> atualizarProduto(
            @RequestBody final Produto produto,
            @PathVariable final String codigo) {
        return ResponseEntity.ok(produtoUserCase.atualizarProduto(produto, codigo));
    }


    @Operation(
            description = "Deleta produto por codigo",
            responses = {
                    @ApiResponse(responseCode = "203", description = "Produtos deletado")
            }
    )
    @DeleteMapping(value = "/{codigo}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> deletarProduto(@PathVariable final String codigo) {
        produtoUserCase.deletarProduto(codigo);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Retorna produtos pela categoria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso!")
            }
    )
    @GetMapping(value = "/{categoria}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Produto>> consultarProdutoPorCategoria(@PathVariable final String categoria) {
        return ResponseEntity.ok(produtoUserCase.consultarProdutoPorCategoria(categoria));
    }

    @Operation(
            description = "Retorna todos os produtos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso!")
            }
    )
    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Produto>> consultarTodosProdutos() {
        return ResponseEntity.ok(produtoUserCase.consultarTodosProdutos());
    }
}
