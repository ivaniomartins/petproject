package com.petproject.martins.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemAtendimentoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idItem;

    @NotNull(message = "O procedimento é obrigatório")
    private ProcedimentoDto procedimento;

    @NotNull(message = "O atendimento é obrigatório")
    private AtendimentoDto atendimento;

    public ItemAtendimentoDto() {
    }
}