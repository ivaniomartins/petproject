package com.petproject.martins.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.martins.model.Atendimento;
import com.petproject.martins.model.ItemAtendimento;
import com.petproject.martins.repositories.ItemAtendimentoRepository;

@Service
public class ItemAtendimentoService {
	
	@Autowired
	ItemAtendimentoRepository  repo;
	
	
 public  ItemAtendimento buscar(Long id) {
	
	 Optional<ItemAtendimento> obj = repo.findById(id);
	 
	 return obj.orElse(null);
	 
 }
 
 public Iterable<ItemAtendimento> buscarTodos () {
	 
	 return repo.findAll();
	 
 }

}
