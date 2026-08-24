package com.petproject.martins.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petproject.martins.model.Tutor;
import com.petproject.martins.model.dto.TutorDto;
import com.petproject.martins.model.mapper.TutorMapper;
import com.petproject.martins.repositories.TutorRepository;
import com.petproject.martins.services.exceptions.ObjectNotFoundException;

@Service
public class TutorService {

	private final TutorRepository repo;
	private final TutorMapper tutorMapper = TutorMapper.INSTANCE;

	public TutorService(TutorRepository repo) {
		this.repo = repo;
	}

	@Transactional(readOnly = true)
	public TutorDto find(Long id) {
		Tutor obj = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(
						"Tutor não encontrado: " + id + ", Tipo: " + Tutor.class.getName()));
		return tutorMapper.toDto(obj);
	}

	@Transactional(readOnly = true)
	public List<TutorDto> findAll() {
		return repo.findAll().stream()
				.map(tutorMapper::toDto)
				.collect(Collectors.toList());
	}

	@Transactional
	public TutorDto createTutor(TutorDto tutorDto) {
		Tutor tutor = tutorMapper.toEntity(tutorDto);
		Tutor savedTutor = repo.save(tutor);
		return tutorMapper.toDto(savedTutor);
	}

	@Transactional
	public TutorDto updateTutor(Long id, TutorDto tutorDto) {
		Tutor tutor = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Tutor não encontrado: " + id));
		tutor.setNome(tutorDto.getNome());
		tutor.setEmail(tutorDto.getEmail());
		tutor.setCpf(tutorDto.getCpf());
		Tutor tutorUpdate = repo.save(tutor);
		return tutorMapper.toDto(tutorUpdate);
	}

	@Transactional
	public void deleteTutor(Long id) {
		repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Tutor não encontrado: " + id));
		repo.deleteById(id);
	}
}