package com.petproject.martins.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.martins.model.Procedimento;
import com.petproject.martins.repositories.ProcedimentoRepository;

@Service
public class ProcedimentoService {
	@Autowired
	ProcedimentoRepository repo;
	

	public Procedimento find (Long id) {
	
		Optional<Procedimento> proc = repo.findById(id);
		
		return proc.orElse(null);
		
	}
	
	
}
