package com.petproject.martins.model.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProcedimentoDto {

    private Long cdProcedimento;
    @NotBlank(message = "O nome do procedimento é obrigatório")
    @Length(min = 5)
    private String nmProcedimento;
    @NotBlank
    private Double vlProcedimento;

}
