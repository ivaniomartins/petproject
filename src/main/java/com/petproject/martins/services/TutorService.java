package com.petproject.martins.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.martins.model.Tutor;
import com.petproject.martins.repositories.TutorRepository;

@Service
public class TutorService {
	
	@Autowired
	private TutorRepository repo;
	
	public Tutor buscar (Long id) {
		
		Optional<Tutor> obj = repo.findById(id);
		
		return obj.orElse(null);
		}
	
	
	public List<Tutor> findAll() {
		
		return repo.findAll();
	}
	
	
}
