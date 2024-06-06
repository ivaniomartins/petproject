package com.petproject.martins.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.petproject.martins.model.Tutor;
import com.petproject.martins.model.dto.TutorDto;

@Mapper
public interface TutorMapper {

    TutorMapper INSTANCE = Mappers.getMapper(TutorMapper.class);

    TutorDto toDto(Tutor tutor);

    Tutor toEntity(TutorDto tutorDto);
}
