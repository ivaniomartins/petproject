package com.petproject.martins.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petproject.martins.model.Paciente;
import com.petproject.martins.model.dto.PacienteDto;
import com.petproject.martins.services.PacienteService;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteResource {

	@Autowired
	public PacienteService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {

		PacienteDto pac = service.find(id);
		return ResponseEntity.ok().body(pac);

	}

	@GetMapping
	public ResponseEntity<List<PacienteDto>> findAll() {

		List<PacienteDto> list = service.findAll();
		return ResponseEntity.ok().body(list);

	}

}
