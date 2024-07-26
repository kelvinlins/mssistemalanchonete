package com.fiap.mssistemalanchonete.dataprovider.mapper;

import org.mapstruct.MappingTarget;

public interface EntityMapper<E,D> {
    E toEntity(D domain);
    D toDomain(E entity);
    void merge(D domain, @MappingTarget E entity);
}
