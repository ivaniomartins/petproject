package com.petproject.martins.model.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProcedimentoDto {

    private Long cdProcedimento;
    @NotBlank(message = "O nome do procedimento é obrigatório")
    @Length(min = 5)
    private String nmProcedimento;

    @NotNull(message = "O valor do procedimento é obrigatório")
    @Positive(message = "O valor do procedimento deve ser maior que zero")
    private Double vlProcedimento;

}
