package com.petproject.martins.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.petproject.martins.model.Paciente;
import com.petproject.martins.model.dto.PacienteDto;

@Mapper
public interface PacienteMapper {

    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    // PacienteDto não tem 'atendimentos' nem 'especie' (a entidade armazena o cod
    // do enum em uma String) — MapStruct ignora automaticamente.
    PacienteDto toDto(Paciente paciente);

    Paciente toEntity(PacienteDto pacienteDto);

}