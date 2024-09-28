package com.fiap.mssistemalanchonete.entrypoint.controller;

import com.fiap.mssistemalanchonete.core.exception.ErrorResponse;
import com.fiap.mssistemalanchonete.core.usecase.PagamentoUseCaseFacade;
import com.fiap.mssistemalanchonete.dataprovider.mapper.PagamentoDtoMapper;
import com.fiap.mssistemalanchonete.entrypoint.dto.PagamentoRequestDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.PagamentoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoUseCaseFacade pagamentoUseCaseFacade;
    @Autowired
    private PagamentoDtoMapper pagamentoDtoMapper;

    @Operation(
            description = "Webhook para realização do pagamento e recebimento da confirmaçao",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pagamento realizado com sucesso!"),
                    @ApiResponse(responseCode = "404",
                            description = "Pedido não encontrado!",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping(produces = "application/json")
    public ResponseEntity<PagamentoResponseDto> realizarPagamento(@RequestBody final PagamentoRequestDto pagamentoRequestDto) throws Exception {
        return ResponseEntity.ok(pagamentoDtoMapper.toDto(pagamentoUseCaseFacade.realizarPagamento(pagamentoRequestDto.codigoPedido())));
    }
}
