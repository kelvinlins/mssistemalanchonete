package com.fiap.mssistemalanchonete.adapter.out.mapper;

import com.fiap.mssistemalanchonete.adapter.out.entity.PedidoEntity;
import com.fiap.mssistemalanchonete.core.domain.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
  componentModel = "spring",
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  uses = {ClienteMapper.class, ProdutoMapper.class}
)
public interface PedidoMapper extends EntityMapper<PedidoEntity, Pedido>{

  @Override
  PedidoEntity toEntity(Pedido domain);
}
