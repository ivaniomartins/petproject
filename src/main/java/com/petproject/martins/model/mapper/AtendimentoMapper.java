package com.petproject.martins.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.petproject.martins.model.Atendimento;
import com.petproject.martins.model.dto.AtendimentoDto;

@Mapper
public interface AtendimentoMapper {

    AtendimentoMapper INSTANCE = Mappers.getMapper(AtendimentoMapper.class);

    // Ignora 'itens' no mapeamento — coleção lazy deve ser carregada sob demanda.
    @Mapping(target = "itens", ignore = true)
    AtendimentoDto toDto(Atendimento atendimento);

    @Mapping(target = "itens", ignore = true)
    Atendimento toEntity(AtendimentoDto dto);
}
