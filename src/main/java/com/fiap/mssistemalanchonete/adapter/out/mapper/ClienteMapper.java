package com.fiap.mssistemalanchonete.adapter.out.mapper;

import com.fiap.mssistemalanchonete.adapter.out.entity.ClienteEntity;
import com.fiap.mssistemalanchonete.core.domain.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper extends EntityMapper<ClienteEntity, Cliente> {
}