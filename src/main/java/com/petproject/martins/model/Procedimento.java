package com.petproject.martins.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Procedimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdProcedimento;
	private String nmProcedimento;
	private Double vlProcedimento;

	public Procedimento() {

	}

	public Procedimento(Long cdProcedimento, String nmProcedimento, Double vlProcedimento) {
		this.cdProcedimento = cdProcedimento;
		this.nmProcedimento = nmProcedimento;
		this.vlProcedimento = vlProcedimento;

	}

}
