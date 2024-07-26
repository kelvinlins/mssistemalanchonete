package com.fiap.mssistemalanchonete.dataprovider.mapper;

import com.fiap.mssistemalanchonete.entrypoint.dto.AcompanhamentoClienteResponseDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.AcompanhamentoCozinhaResponseDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.AdicionarProdutoRequestDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.AtualizaPedidoRequestDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.ComboAcompanhamentoCozinhaDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.ComboDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.CriarComboRequestDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.CriarPedidoRequestDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.ItemComboAcompanhamentoCozinhaDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.ItemComboDto;
import com.fiap.mssistemalanchonete.entrypoint.dto.PedidoResponseDto;
import com.fiap.mssistemalanchonete.core.model.Cliente;
import com.fiap.mssistemalanchonete.core.model.Combo;
import com.fiap.mssistemalanchonete.core.model.Pedido;
import com.fiap.mssistemalanchonete.core.model.Produto;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(
  componentModel = "spring",
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PedidoDtoMapper {

  @Mapping(target = "cliente", expression = "java(toDomainCliente(dto.codigoCliente()))")
  Pedido toDomain(CriarPedidoRequestDto dto);

  @Mapping(target = "cliente", expression = "java(pedido.getCodigoClienteAsString())")
  PedidoResponseDto toPedidoResponseDto(Pedido pedido);

  Pedido toDomain(AtualizaPedidoRequestDto dto);

  @Mapping(target = "combos", expression = "java(toDomainCombos(dto.itens()))")
  Pedido toDomain(CriarComboRequestDto dto);

  default List<Combo> toDomainCombos(List<AdicionarProdutoRequestDto> itens){
    if (Objects.isNull(itens)){
      return null;
    }

    Combo combo = new Combo();
    itens.stream()
      .filter(Objects::nonNull)
      .filter(item-> StringUtils.isNotBlank(item.codigoProduto()) &&
        Objects.nonNull(item.quantidade()))
      .forEach(item -> combo.getItens().put(
        Produto.builder().codigo(item.codigoProduto()).build(), item.quantidade()));

    List<Combo> combos = new ArrayList<>();
    combos.add(combo);
    return combos;
  }

  default Cliente toDomainCliente(Long codigoCliente){
    if (ObjectUtils.isEmpty(codigoCliente)){
      return null;
    }
    return Cliente.builder().codigo(codigoCliente).build();
  }

  default Page<AcompanhamentoClienteResponseDto> toAcompanhamentoClienteResponse(Page<Pedido> pedidos){
    return pedidos.map(this::toAcompanhamentoClienteResponse);
  };

  @Mapping(target = "tempoEspera", expression = "java(pedido.getTempoEspera())")
  AcompanhamentoClienteResponseDto toAcompanhamentoClienteResponse(Pedido pedido);

  default Page<AcompanhamentoCozinhaResponseDto> toAcompanhamentoCozinhaResponse(Page<Pedido> pedidos){
    return pedidos.map(this::toAcompanhamentoCozinhaResponse);
  }

  @Mapping(target = "tempoEspera", expression = "java(pedido.getTempoEspera())")
  AcompanhamentoCozinhaResponseDto toAcompanhamentoCozinhaResponse(Pedido pedido);

  default ComboAcompanhamentoCozinhaDto toComboAcompanhamentoCozinhaDto(Combo combo){
    if (Objects.isNull(combo)){
      return null;
    }
    List<ItemComboAcompanhamentoCozinhaDto> itensDto = new ArrayList<>();
    combo.getItens().forEach(
      (produto, quantidade) -> itensDto.add(
        new ItemComboAcompanhamentoCozinhaDto(
          produto.getCodigo(),
          produto.getNome(),
          produto.getDescricao(),
          produto.getCategoria(),
          quantidade
        )
      )
    );
    return new ComboAcompanhamentoCozinhaDto(combo.getId(),itensDto);
  };

  default ComboDto toComboDto(Combo combo){
    if (Objects.isNull(combo)){
      return null;
    }
    List<ItemComboDto> itensDto = new ArrayList<>();
    if (Objects.nonNull(combo.getItens())){
      combo.getItens().forEach(
        (produto, quantidade) -> itensDto.add(
          new ItemComboDto(
            produto.getCodigo(),
            produto.getNome(),
            produto.getDescricao(),
            produto.getCategoria(),
            quantidade,
            produto.getPreco()
          )
        )
      );
    }
    return new ComboDto(combo.getId(), itensDto, combo.getSubTotal());
  };

  default Page<PedidoResponseDto> toPagePedidoResponseDto(Page<Pedido> pedidos){
    return pedidos.map(this::toPedidoResponseDto);
  };
}
