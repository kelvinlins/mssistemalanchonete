package com.fiap.mssistemalanchonete.adapter.in.controller.mapper;

import com.fiap.mssistemalanchonete.adapter.in.controller.dto.AtualizaPedidoRequestDto;
import com.fiap.mssistemalanchonete.adapter.in.controller.dto.CriarPedidoRequestDto;
import com.fiap.mssistemalanchonete.adapter.in.controller.dto.PedidoResponseDto;
import com.fiap.mssistemalanchonete.core.domain.model.Pedido;
import com.fiap.mssistemalanchonete.core.domain.model.Produto;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(
  componentModel = "spring",
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PedidoDtoMapper {

  @Mapping(target = "cliente.codigo", source = "codigoCliente")
  Pedido toDomain(CriarPedidoRequestDto dto);

  PedidoResponseDto toPedidoResponseDto(Pedido pedido);

  @Mapping(target = "itens", expression = "java(toDomainItens(dto.codigoItens()))")
  Pedido toDomain(AtualizaPedidoRequestDto dto);

  default List<Produto> toDomainItens(List<String> codigos){
    if (Objects.isNull(codigos)){
      return null;
    }
    return codigos.stream()
      .filter(StringUtils::isNotBlank)
      .map(codigo -> Produto.builder().codigo(codigo).build())
      .collect(Collectors.toList());
  }
}
