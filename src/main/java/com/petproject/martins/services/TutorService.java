package com.petproject.martins.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.martins.model.Tutor;
import com.petproject.martins.model.dto.TutorDto;
import com.petproject.martins.repositories.TutorRepository;

import jakarta.transaction.Transactional;

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
	
    @Transactional	
	public Tutor insert (Tutor obj) {
	
		obj.setCodTutor(null);
		return repo.save(obj);
		
		
	}
	public Tutor updateById(Tutor tutor) {
		Optional<Tutor> obj = repo.findById(tutor.getCodTutor());
		
		if(obj.isPresent()) {
			Tutor t = obj.get();
			if (tutor.getNome()!=null) {
				t.setNome(tutor.getNome());
			}
			if(tutor.getEmail()!= null) {
				t.setEmail(tutor.getEmail());
			}
		}
		
		return repo.save(tutor);
		
	}
	
	public TutorDto fromDto(Tutor tutor) {
		
	 TutorDto dto = new TutorDto();
	 dto.setCodTutor(tutor.getCodTutor());
	 dto.setNome(tutor.getNome());
	 dto.setEmail(tutor.getEmail());
	
	 
	 return dto;
		
	}
	
	public static List<TutorDto> convertListToDto(List<Tutor> tutors){
		
		return tutors.stream().map(TutorDto::new).collect(Collectors.toList());
		
	}
	
}
