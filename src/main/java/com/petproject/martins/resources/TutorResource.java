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
import com.petproject.martins.services.TutorService;

@RestController
@RequestMapping(value= "/tutores")
public class TutorResource {
	
	@Autowired
	private TutorService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id){
		
		Tutor obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List <Tutor>> findAll(){
		
		List<Tutor> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<Void> insert (@RequestBody Tutor obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getCodTutor()).toUri();
		
		return ResponseEntity.created(uri).build(); 
	} 
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update (@RequestBody Tutor obj, @PathVariable Long id){
		
		Tutor tutor = service.buscar(id);
		 tutor.setCodTutor(id);
		 tutor = service.updateById(tutor);
		 
		 return ResponseEntity.noContent().build();
	}

}
