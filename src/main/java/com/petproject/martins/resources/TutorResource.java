package com.petproject.martins.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.petproject.martins.model.Tutor;
import com.petproject.martins.model.dto.TutorDto;
import com.petproject.martins.services.TutorService;

@RestController
@RequestMapping(value = "/tutores")
public class TutorResource {

	@Autowired
	private TutorService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TutorDto> find(@PathVariable Long id) {

		TutorDto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TutorDto>> listAll() {

		List<TutorDto> list = service.findAll();

		return ResponseEntity.ok().body(list);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody TutorDto dto) {
		dto = service.createTutor(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getCodTutor()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<TutorDto> update(@RequestBody TutorDto tutorDto, @PathVariable Long id) {

		TutorDto updateTutorDto = service.updateTutor(id, tutorDto);
		if (updateTutorDto != null) {
			return ResponseEntity.ok(updateTutorDto);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
