package com.fiap.mssistemalanchonete.entrypoint.controller;

import com.fiap.mssistemalanchonete.core.exception.ErrorResponse;
import com.fiap.mssistemalanchonete.core.model.Pedido;
import com.fiap.mssistemalanchonete.core.enums.StatusPedidoEnum;
import com.fiap.mssistemalanchonete.core.usecase.PedidoUseCaseFacade;
import com.fiap.mssistemalanchonete.dataprovider.mapper.PedidoDtoMapper;
import com.fiap.mssistemalanchonete.entrypoint.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoUseCaseFacade pedidoUseCaseFacade;

    private final PedidoDtoMapper pedidoDtoMapper;

    @Autowired
    public PedidoController(PedidoUseCaseFacade pedidoUseCaseFacade, PedidoDtoMapper pedidoDtoMapper){
        this.pedidoUseCaseFacade = pedidoUseCaseFacade;
        this.pedidoDtoMapper = pedidoDtoMapper;
    }

    @Operation(
      description = "Cria novo pedido",
      responses = {
        @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso!"),
        @ApiResponse(responseCode = "404",
          description = "Cliente não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PedidoResponseDto> cadastrarPedido(
      @RequestBody final CriarPedidoRequestDto request, UriComponentsBuilder uriComponentsBuilder) throws Exception {
        Pedido pedido = pedidoDtoMapper.toDomain(request);
        Pedido pedidoCriado = pedidoUseCaseFacade.criarPedido(pedido);
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
      description = "Atualiza o status de um pedido existente",
      responses = {
        @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso!"),
        @ApiResponse(responseCode = "404",
          description = "Pedido não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @PatchMapping(value = "/{codigoPedido}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PedidoResponseDto> atualizarPedido(
      @RequestBody final AtualizaPedidoRequestDto request,
      @PathVariable final String codigoPedido) throws Exception {
        var pedido = pedidoDtoMapper.toDomain(request);
        return ResponseEntity.ok(
          pedidoDtoMapper.toPedidoResponseDto(
                  pedidoUseCaseFacade.atualizarPedido(pedido, codigoPedido)
          )
        );
    }

    @Operation(
      description = "Atualiza o status de um pedido existente",
      responses = {
        @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso!"),
        @ApiResponse(responseCode = "404",
          description = "Pedido não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @GetMapping(value = "/{codigoPedido}", produces = "application/json")
    public ResponseEntity<PedidoResponseDto> getPedido(
      @PathVariable final String codigoPedido) throws Exception {
        return ResponseEntity.ok(
          pedidoDtoMapper.toPedidoResponseDto(
                  pedidoUseCaseFacade.getPedidoPorCodigo(codigoPedido)
          )
        );
    }

    @Operation(
      description = "Adiciona novo combo ao pedido",
      responses = {
        @ApiResponse(responseCode = "201", description = "Combo criado com sucesso!"),
        @ApiResponse(responseCode = "404",
          description = "Recurso não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "406",
          description = "Status do Pedido não permite alteração!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "400",
          description = "Quantidade de produto inválida!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @PostMapping(value = "/{codigoPedido}/combos",consumes = "application/json", produces = "application/json")
    public ResponseEntity<CriarComboResponseDto> criarCombo(
      @RequestBody final CriarComboRequestDto request, @PathVariable final String codigoPedido) throws Exception {
        Pedido pedido = pedidoDtoMapper.toDomain(request);
        var idComboAdicionado = pedidoUseCaseFacade.adicionarCombo(codigoPedido, pedido);
        return ResponseEntity.ok(
          new CriarComboResponseDto(idComboAdicionado)
        );
    }

    @Operation(
      description = "Adiciona um produto a um pedido existente",
      responses = {
        @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso!"),
        @ApiResponse(responseCode = "404",
          description = "Recurso não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "406",
          description = "Status do Pedido não permite alteração!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "400",
          description = "Quantidade de produto inválida!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @PostMapping(value = "/{codigoPedido}/combos/{comboId}/produtos", consumes = "application/json",
      produces = "application/json")
    public ResponseEntity<PedidoResponseDto> adicionarProdutos(
      @RequestBody final AdicionarProdutoRequestDto request,
      @PathVariable final String codigoPedido,
      @PathVariable final Integer comboId) throws Exception {
        var pedido = pedidoUseCaseFacade.adicionarProdutos(codigoPedido, comboId, request.codigoProduto(), request.quantidade());
        return ResponseEntity.ok(
          pedidoDtoMapper.toPedidoResponseDto(pedido)
        );
    }

    @Operation(
      description = "Remove uma quantidade de um produto de um combo existente",
      responses = {
        @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso!"),
        @ApiResponse(responseCode = "406",
          description = "Status do Pedido não permite alteração!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404",
          description = "Recurso não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "400",
          description = "Quantidade de produto inválida!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @PatchMapping(value = "/{codigoPedido}/combos/{comboId}/produtos/{codigoProduto}",consumes = "application/json", produces = "application/json")
    public ResponseEntity<PedidoResponseDto> removerProdutos(
      @PathVariable final String codigoProduto,
      @PathVariable final String codigoPedido,
      @PathVariable final Integer comboId,
      @RequestBody RemoverProdutosRequestDto requestDto) throws Exception {
        var pedido = pedidoUseCaseFacade.removerProduto(codigoPedido, comboId, codigoProduto, requestDto.quantidade());
        return ResponseEntity.ok(
          pedidoDtoMapper.toPedidoResponseDto(pedido)
        );
    }


    @Operation(
      description = "Deleta pedido por codigo",
      responses = {
        @ApiResponse(responseCode = "204", description = "Pedidos deletado"),
        @ApiResponse(responseCode = "404",
          description = "Pedido não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @DeleteMapping(value = "/{codigo}", produces = "application/json")
    public ResponseEntity<Object> deletarPedido(@PathVariable final String codigo) throws Exception {
        pedidoUseCaseFacade.deletarPedido(codigo);
        return ResponseEntity.noContent().build();
    }

    @Operation(
      description = "Deleta o combo identificado do pedido",
      responses = {
        @ApiResponse(responseCode = "204", description = "Pedidos deletado"),
        @ApiResponse(responseCode = "404",
          description = "Recurso não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @DeleteMapping(value = "/{codigoPedido}/combos/{comboId}", produces = "application/json")
    public ResponseEntity<Object> deletarCombo(@PathVariable final String codigoPedido,
                                               @PathVariable final Integer comboId) throws Exception {
        pedidoUseCaseFacade.deletarCombo(codigoPedido, comboId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
      description = "Retorna uma page de pedidos",
      responses = {
        @ApiResponse(responseCode = "200", description = "Pedidos retornados com sucesso!")
      }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<PedidoResponseDto>> consultarPedidos(
      @PageableDefault(size = 50, sort = {"horaCheckout"}) Pageable pageable) throws Exception {
        return ResponseEntity.ok(
          pedidoDtoMapper.toPagePedidoResponseDto(pedidoUseCaseFacade.listarPedidos(pageable)
          )
        );
    }

    @Operation(
      description = "Retorna uma page de pedidos para acompanhamento dos clientes",
      responses = {
        @ApiResponse(responseCode = "200", description = "Pedidos retornados com sucesso!")
      }
    )
    @GetMapping(value = "/acompanhamento-cliente", produces = "application/json")
    public ResponseEntity<Page<AcompanhamentoClienteResponseDto>> acompanharPedidosCliente(
      @PageableDefault(size = 50, sort = {"horaCheckout"}) Pageable pageable) throws Exception {
        Page<Pedido> pedidos = pedidoUseCaseFacade.listarPedidosPorStatus(pageable, StatusPedidoEnum.getStatusAcompanhar());
        return ResponseEntity.ok(pedidoDtoMapper.toAcompanhamentoClienteResponse(pedidos));
    }

    @Operation(
      description = "Retorna uma page de pedidos para acompanhamento da cozinha",
      responses = {
        @ApiResponse(responseCode = "200", description = "Pedidos retornados com sucesso!")
      }
    )
    @GetMapping(value = "/acompanhamento-cozinha", produces = "application/json")
    public ResponseEntity<Page<AcompanhamentoCozinhaResponseDto>> acompanharPedidosCozinha(
      @PageableDefault(size = 50, sort = {"horaCheckout"}) Pageable pageable) throws Exception {
        Page<Pedido> pedidos = pedidoUseCaseFacade.listarPedidosPorStatus(pageable, StatusPedidoEnum.getStatusAcompanhar());
        return ResponseEntity.ok(pedidoDtoMapper.toAcompanhamentoCozinhaResponse(pedidos));
    }


    @Operation(
      description = "Consulta status de pagamento do pedido",
      responses = {
        @ApiResponse(responseCode = "200", description = "Combo criado com sucesso!"),
        @ApiResponse(responseCode = "404",
          description = "Pedido não encontrado!",
          content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
    )
    @GetMapping(value = "/{codigoPedido}/pagamento", produces = "application/json")
    public ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(@PathVariable final String codigoPedido) throws Exception {
        return ResponseEntity.ok(pedidoDtoMapper.toStatusPagamentoDto(pedidoUseCaseFacade.getStatusPagamento(codigoPedido)));
    }

    @Operation(
            description = "Finaliza pedido",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Combo criado com sucesso!"),
                    @ApiResponse(responseCode = "406",
                            description = "Status do Pedido não permite alteração!",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Pedido não encontrado!",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping(value = "/{codigoPedido}/checkout", produces = "application/json")
    public ResponseEntity<PedidoResponseDto> checkout(@PathVariable final String codigoPedido) throws Exception {
        return ResponseEntity.ok(pedidoDtoMapper.toPedidoResponseDto(pedidoUseCaseFacade.checkout(codigoPedido)));
    }

}