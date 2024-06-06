package com.petproject.martins.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.martins.model.Atendimento;
import com.petproject.martins.repositories.AtendimentoRepository;

@Service
public class AtendimentoService {

	@Autowired
	public AtendimentoRepository repo;
	
	
	public Atendimento buscar (Long id) {
		
		Optional<Atendimento> obj = repo.findById(id);
		return obj.orElseGet(null);
		
	}
	
	public Iterable<Atendimento> buscarTodos(){
			
	return repo.findAll();
		
	}
	
	
	
}
