package com.petproject.martins.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petproject.martins.model.Paciente;
import com.petproject.martins.services.PacienteService;

@RestController
@RequestMapping(value="/pacientes")
public class PacienteResource {
	
	@Autowired
	public PacienteService serv;
	
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id){
		
		Paciente pac = serv.buscar(id);
		return ResponseEntity.ok().body(pac);
		
		
	}
   
   @RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<Paciente>> findAll(){
		
		List<Paciente> list = serv.findAll();
		return ResponseEntity.ok().body(list);
		
		
	}

}
