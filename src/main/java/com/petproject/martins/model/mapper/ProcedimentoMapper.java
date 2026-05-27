package com.petproject.martins.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.petproject.martins.model.Procedimento;
import com.petproject.martins.model.dto.ProcedimentoDto;

@Mapper
public interface ProcedimentoMapper {

    ProcedimentoMapper INSTANCE = Mappers.getMapper(ProcedimentoMapper.class);

    ProcedimentoDto toDto(Procedimento procedimento);

    Procedimento toEntity(ProcedimentoDto procedimentoDto);

}
