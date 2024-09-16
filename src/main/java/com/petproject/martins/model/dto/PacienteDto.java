package com.petproject.martins.model.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PacienteDto {

    @NotBlank
    private Long cdPaciente;
    @NotBlank(message = "O nome do paciente é obrigatório")
    @Length(min = 3)
    private String nmPaciente;
    @NotBlank
    private String raca;
    @NotBlank
    private Double peso;
    @DateTimeFormat
    private Date dtNascimento;

    @NotBlank
    private TutorDto tutor;

    public PacienteDto() {

    }

}
