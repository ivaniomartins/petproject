package com.petproject.martins.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.petproject.martins.model.Paciente;
import com.petproject.martins.model.dto.PacienteDto;

@Mapper
public interface PacienteMapper {

    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    PacienteDto toDto(Paciente paciente);

    Paciente toEntity(PacienteDto pacienteDto);

}
