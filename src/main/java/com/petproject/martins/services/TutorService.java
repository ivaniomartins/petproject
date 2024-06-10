package com.petproject.martins.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.martins.model.Tutor;
import com.petproject.martins.model.dto.TutorDto;
import com.petproject.martins.model.mapper.TutorMapper;
import com.petproject.martins.repositories.TutorRepository;
import com.petproject.martins.services.exceptions.ObjectNotFoundException;

@Service
public class TutorService {

	@Autowired
	private TutorRepository repo;

	public final TutorMapper tutorMapper = TutorMapper.INSTANCE;

	public TutorDto find(Long id) {
		Optional<Tutor> obj = repo.findById(id);
		return obj.map(tutorMapper::toDto)
				.orElseThrow(() -> new ObjectNotFoundException(
						"Tutor não encontrado: " + id + ", Tipo: " + Tutor.class.getName()));
	}

	public List<TutorDto> findAll() {
		return repo.findAll().stream()
				.map(tutorMapper::toDto)
				.collect(Collectors.toList());
	}

	public TutorDto createTutor(TutorDto tutorDto) {
		Tutor tutor = tutorMapper.toEntity(tutorDto);
		Tutor savedTutor = repo.save(tutor);

		return tutorMapper.toDto(savedTutor);
	}

	public TutorDto updateTutor(Long id, TutorDto tutorDto) {

		Optional<Tutor> tutorOptional = repo.findById(id);
		if (tutorOptional.isPresent()) {
			Tutor tutor = tutorOptional.get();
			tutor.setNome(tutorDto.getNome());
			tutor.setEmail(tutorDto.getEmail());

			Tutor tutorupdate = repo.save(tutor);
			return tutorMapper.toDto(tutorupdate);

		} else {
			return null;
		}
	}

	public boolean deleteTutor(Long id) {
		Optional<Tutor> tutor = repo.findById(id);
		if (tutor.isPresent()) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
}