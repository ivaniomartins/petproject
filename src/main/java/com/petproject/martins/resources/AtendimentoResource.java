package com.petproject.martins.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petproject.martins.model.Atendimento;
import com.petproject.martins.services.AtendimentoService;

@RestController
@RequestMapping(value="/atendimentos")
public class AtendimentoResource {
	
	@Autowired
	public AtendimentoService serv;
	
	@RequestMapping(value= "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Atendimento> find(@PathVariable Long id){
		
		
		Atendimento atendime = serv.buscar(id);
		
		return ResponseEntity.ok().body(atendime);
		
	}

}
