package com.petproject.martins.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.petproject.martins.model.dto.TutorDto;
import com.petproject.martins.services.TutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tutores")
public class TutorResource {

	@Autowired
	private TutorService service;

	@GetMapping("/{id}")
	public ResponseEntity<TutorDto> find(@PathVariable Long id) {

		TutorDto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<TutorDto>> listAll() {

		List<TutorDto> list = service.findAll();

		return ResponseEntity.ok().body(list);

	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody TutorDto dto) {
		dto = service.createTutor(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getCodTutor()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<TutorDto> update(@RequestBody TutorDto tutorDto, @PathVariable Long id) {

		TutorDto updateTutorDto = service.updateTutor(id, tutorDto);
		if (updateTutorDto != null) {
			return ResponseEntity.ok(updateTutorDto);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		boolean deleteRecord = service.deleteTutor(id);
		return deleteRecord ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

	}

}
