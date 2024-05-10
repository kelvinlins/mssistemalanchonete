package com.fiap.mssistemalanchonete.adapter.out.mapper;

import com.fiap.mssistemalanchonete.adapter.out.entity.ProdutoEntity;
import com.fiap.mssistemalanchonete.core.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProdutoMapper extends EntityMapper<ProdutoEntity, Produto>{
}
