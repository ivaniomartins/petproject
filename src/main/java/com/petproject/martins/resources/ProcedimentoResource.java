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

import com.petproject.martins.model.dto.ProcedimentoDto;
import com.petproject.martins.services.ProcedimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/procedimentos")
public class ProcedimentoResource {

	private final ProcedimentoService serv;

	public ProcedimentoResource(ProcedimentoService serv) {
		this.serv = serv;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProcedimentoDto> find(@PathVariable Long id) {
		ProcedimentoDto proc = serv.find(id);
		return ResponseEntity.ok().body(proc);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ProcedimentoDto dto) {
		dto = serv.createProcedimento(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getCdProcedimento()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<ProcedimentoDto>> listAll() {
		List<ProcedimentoDto> list = serv.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProcedimentoDto> update(@PathVariable Long id, @Valid @RequestBody ProcedimentoDto dto) {
		ProcedimentoDto updated = serv.updateProcedimento(id, dto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		serv.deleteProcedimento(id);
		return ResponseEntity.noContent().build();
	}
}