package com.petproject.martins.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petproject.martins.model.Procedimento;
import com.petproject.martins.model.dto.ProcedimentoDto;
import com.petproject.martins.services.ProcedimentoService;

import jakarta.persistence.PostUpdate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/id")
public class ProcedimentoResource {

	@Autowired
	ProcedimentoService serv;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {

		Procedimento proc = serv.find(id);

		return ResponseEntity.ok().body(proc);

	}

	@PutMapping("path/{id}")
	public ResponseEntity<ProcedimentoDto> update(@PathVariable Long id, @RequestBody String entity) {
		ProcedimentoDto updatedProcedimentoDto = serv.updateProcedimento(id, null);
		if (updatedProcedimentoDto != null) {
			return ResponseEntity.ok(updatedProcedimentoDto);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		boolean deleteRecord = serv.deleteProcedimento(id);
		return deleteRecord ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

	}

}
