package com.petproject.martins.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AtendimentoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cdAtendimento;

    @NotNull(message = "A data do atendimento é obrigatória")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dtAtendimento;

    @NotNull(message = "O paciente é obrigatório")
    private PacienteDto paciente;

    private List<ItemAtendimentoDto> itens;

    public AtendimentoDto() {
    }
}