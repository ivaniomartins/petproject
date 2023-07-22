package com.petproject.martins.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petproject.martins.model.Procedimento;
import com.petproject.martins.services.ProcedimentoService;

@RestController
@RequestMapping(value="/id")
public class ProcedimentoResource {

	@Autowired
	ProcedimentoService serv;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find (@PathVariable Long id) {
		
		Procedimento proc = serv.find(id);
		
		return ResponseEntity.ok().body(proc);
		
	}
	
}
