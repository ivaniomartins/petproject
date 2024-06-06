package com.petproject.martins.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TutorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codTutor;

	@NotBlank(message = "O nome do tutor é obrigatório")
	private String nome;

	@Email(message = "Email inválido")
	private String email;

	public TutorDto() {

	}

}