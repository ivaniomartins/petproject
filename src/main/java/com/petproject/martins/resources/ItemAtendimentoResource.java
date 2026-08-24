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

import com.petproject.martins.model.dto.ItemAtendimentoDto;
import com.petproject.martins.services.ItemAtendimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/itens")
public class ItemAtendimentoResource {

	private final ItemAtendimentoService service;

	public ItemAtendimentoResource(ItemAtendimentoService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemAtendimentoDto> find(@PathVariable Long id) {
		ItemAtendimentoDto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<ItemAtendimentoDto>> findAll() {
		List<ItemAtendimentoDto> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ItemAtendimentoDto dto) {
		dto = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getIdItem())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemAtendimentoDto> update(@PathVariable Long id,
			@Valid @RequestBody ItemAtendimentoDto dto) {
		ItemAtendimentoDto updated = service.update(id, dto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}