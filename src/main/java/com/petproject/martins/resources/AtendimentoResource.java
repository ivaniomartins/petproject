package com.petproject.martins.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.petproject.martins.model.dto.AtendimentoDto;
import com.petproject.martins.services.AtendimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/atendimentos")
public class AtendimentoResource {

	private final AtendimentoService service;

	public AtendimentoResource(AtendimentoService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<AtendimentoDto> find(@PathVariable Long id) {
		AtendimentoDto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<AtendimentoDto>> findAll() {
		List<AtendimentoDto> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody AtendimentoDto dto) {
		dto = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getCdAtendimento())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<AtendimentoDto> update(@PathVariable Long id, @Valid @RequestBody AtendimentoDto dto) {
		AtendimentoDto updated = service.update(id, dto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}