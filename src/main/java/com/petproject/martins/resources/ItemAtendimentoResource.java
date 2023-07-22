package com.petproject.martins.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petproject.martins.model.ItemAtendimento;
import com.petproject.martins.services.ItemAtendimentoService;

@RestController
@RequestMapping(value= "/itens")
public class ItemAtendimentoResource {
	
	@Autowired
	ItemAtendimentoService serv;
	
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value="/{cdItem}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar (@PathVariable Long cdItem){
		
		ItemAtendimento obj = serv.buscar(cdItem);
		return ResponseEntity.ok().body(obj);
		
	} 

}
