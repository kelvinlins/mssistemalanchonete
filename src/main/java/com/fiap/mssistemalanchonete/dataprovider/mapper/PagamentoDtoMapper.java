package com.fiap.mssistemalanchonete.dataprovider.mapper;

import com.fiap.mssistemalanchonete.core.model.Pagamento;
import com.fiap.mssistemalanchonete.entrypoint.dto.PagamentoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PagamentoDtoMapper {
    PagamentoResponseDto toDto(Pagamento pagamento);
}
