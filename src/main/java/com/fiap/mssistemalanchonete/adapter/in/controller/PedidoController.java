package com.fiap.mssistemalanchonete.adapter.in.controller;

import com.fiap.mssistemalanchonete.adapter.in.controller.dto.AdicionarProdutoRequestDto;
import com.fiap.mssistemalanchonete.adapter.in.controller.dto.AtualizaPedidoRequestDto;
import com.fiap.mssistemalanchonete.adapter.in.controller.dto.CriarPedidoRequestDto;
import com.fiap.mssistemalanchonete.adapter.in.controller.dto.PedidoResponseDto;
import com.fiap.mssistemalanchonete.adapter.in.controller.mapper.PedidoDtoMapper;
import com.fiap.mssistemalanchonete.core.domain.model.Pedido;
import com.fiap.mssistemalanchonete.core.userCase.pedido.PedidoUserCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoUserCase pedidoUserCase;
    private final PedidoDtoMapper pedidoDtoMapper;

    @Autowired
    public PedidoController(PedidoUserCase pedidoUserCase, PedidoDtoMapper pedidoDtoMapper){
        this.pedidoUserCase = pedidoUserCase;
      this.pedidoDtoMapper = pedidoDtoMapper;
    }

    @Operation(
            description = "Cria novo pedido",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso!")
            }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PedidoResponseDto> cadastrarPedido(
      @RequestBody final CriarPedidoRequestDto request, UriComponentsBuilder uriComponentsBuilder) throws Exception {
        Pedido pedido = pedidoDtoMapper.toDomain(request);
        Pedido pedidoCriado = pedidoUserCase.criarPedido(pedido);
        URI location = ServletUriComponentsBuilder
          .fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(pedidoCriado.getCodigo())
          .toUri();
        return ResponseEntity.created(location)
                .body(
                  pedidoDtoMapper.toPedidoResponseDto(pedidoCriado)
                );
    }

    @Operation(
            description = "Atualiza um pedido existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso!")
            }
    )
    @PutMapping(value = "/{codigoPedido}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> atualizarPedido(
            @RequestBody final AtualizaPedidoRequestDto request,
            @PathVariable final String codigoPedido) throws Exception {
        var pedido = pedidoDtoMapper.toDomain(request);
        pedidoUserCase.atualizarPedido(pedido, codigoPedido);
        return ResponseEntity.ok("Pedido Atualizado!");
    }

    @Operation(
            description = "Adiciona produtos a um pedido existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso!")
            }
    )
    @PostMapping(value = "/{codigoPedido}/produtos", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> adicionarProdutos(
            @RequestBody final AdicionarProdutoRequestDto request,
            @PathVariable final String codigoPedido) throws Exception {
        var pedido = pedidoUserCase.adicionarProdutos(codigoPedido, request.codigoProdutos());
        return ResponseEntity.ok(pedido);
    }

    @Operation(
            description = "Remove uma ocorrência de um produto de um pedido existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso!")
            }
    )
    @DeleteMapping(value = "/{codigoPedido}/produtos/{codigoProduto}", produces = "application/json")
    public ResponseEntity<Object> removerProdutos(
      @PathVariable final String codigoProduto,
            @PathVariable final String codigoPedido) throws Exception {
        var pedido = pedidoUserCase.removerProduto(codigoPedido, codigoProduto);
        return ResponseEntity.ok(pedido);
    }


    @Operation(
            description = "Deleta pedido por codigo",
            responses = {
                    @ApiResponse(responseCode = "203", description = "Pedidos deletado")
            }
    )
    @DeleteMapping(value = "/{codigo}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> deletarPedido(@PathVariable final String codigo) throws Exception {
        pedidoUserCase.deletarPedido(codigo);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Retorna pedidos pela categoria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedidos retornados com sucesso!")
            }
    )
    @GetMapping(value = "/{categoria}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Pedido>> consultarPedidoPorCategoria(@PathVariable final String categoria) {
        return ResponseEntity.ok(pedidoUserCase.consultarPedidoPorCategoria(categoria));
    }
}
