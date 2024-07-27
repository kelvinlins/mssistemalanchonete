package com.fiap.mssistemalanchonete.entrypoint.controller;

import com.fiap.mssistemalanchonete.core.exception.ErrorResponse;
import com.fiap.mssistemalanchonete.core.model.Produto;
import com.fiap.mssistemalanchonete.core.usecase.PedidoUseCaseFacade;
import com.fiap.mssistemalanchonete.core.usecase.ProdutoUseCaseFacade;
import com.fiap.mssistemalanchonete.core.usecase.produto.ProdutoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoUseCaseFacade produtoUseCaseFacade;

    @Autowired
    public ProdutoController(ProdutoUseCaseFacade produtoUseCaseFacade){
        this.produtoUseCaseFacade = produtoUseCaseFacade;
    }

    @Operation(
            description = "Cria novo produto",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso!"),
                    @ApiResponse(responseCode = "400",
                      description = "Produto já posssui cadastro!",
                      content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Produto> cadastrarProduto(
            @RequestBody final Produto produto) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201))
                .body(produtoUseCaseFacade.salvarProduto(produto));
    }

    @Operation(
            description = "Atualiza um produto existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso!"),
              @ApiResponse(responseCode = "404",
                description = "Produto não encontrado!",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PatchMapping(value = "/{codigo}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Produto> atualizarProduto(
            @RequestBody final Produto produto,
            @PathVariable final String codigo) {
        return ResponseEntity.ok(produtoUseCaseFacade.atualizarProduto(produto, codigo));
    }


    @Operation(
            description = "Deleta produto por codigo",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Produtos deletado"),
              @ApiResponse(responseCode = "404",
                description = "Produto não encontrado!",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @DeleteMapping(value = "/{codigo}", produces = "application/json")
    public ResponseEntity<Object> deletarProduto(@PathVariable final String codigo) {
        produtoUseCaseFacade.deletarProduto(codigo);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Retorna produtos pela categoria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso!")
            }
    )
    @GetMapping(value = "/{categoria}", produces = "application/json")
    public ResponseEntity<List<Produto>> consultarProdutoPorCategoria(@PathVariable final String categoria) {
        return ResponseEntity.ok(produtoUseCaseFacade.consultarProdutoPorCategoria(categoria));
    }

    @Operation(
            description = "Retorna todos os produtos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso!")
            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Produto>> consultarTodosProdutos() {
        return ResponseEntity.ok(produtoUseCaseFacade.consultarTodosProdutos());
    }
}
