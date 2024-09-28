package com.fiap.mssistemalanchonete.dataprovider.mapper;

import com.fiap.mssistemalanchonete.dataprovider.entity.ClienteEntity;
import com.fiap.mssistemalanchonete.core.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ClienteMapper extends EntityMapper<ClienteEntity, Cliente> {
}
