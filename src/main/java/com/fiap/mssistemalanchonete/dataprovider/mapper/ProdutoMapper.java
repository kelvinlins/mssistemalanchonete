package com.fiap.mssistemalanchonete.dataprovider.mapper;

import com.fiap.mssistemalanchonete.dataprovider.entity.ProdutoEntity;
import com.fiap.mssistemalanchonete.core.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProdutoMapper extends EntityMapper<ProdutoEntity, Produto>{
}
