package com.petproject.martins.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.petproject.martins.model.ItemAtendimento;
import com.petproject.martins.model.dto.ItemAtendimentoDto;

@Mapper
public interface ItemAtendimentoMapper {

    ItemAtendimentoMapper INSTANCE = Mappers.getMapper(ItemAtendimentoMapper.class);

    ItemAtendimentoDto toDto(ItemAtendimento entity);

    ItemAtendimento toEntity(ItemAtendimentoDto dto);
}