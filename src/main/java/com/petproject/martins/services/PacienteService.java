package com.petproject.martins.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.martins.model.Paciente;
import com.petproject.martins.model.dto.PacienteDto;
import com.petproject.martins.model.mapper.PacienteMapper;
import com.petproject.martins.repositories.PacienteRepository;
import com.petproject.martins.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;
	@Autowired
	public PacienteMapper pacienteMapper;

	public PacienteDto find(Long id) {

		Paciente obj = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Paciente.class.getName()));
		return pacienteMapper.toDto(obj);

	}

	public List<PacienteDto> findAll() {

		return repo.findAll().stream()
				.map(pacienteMapper::toDto).collect(Collectors.toList());

	}

	public PacienteDto updatePaciente(Long id, PacienteDto pacienteDto) {
		Paciente paciente = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Paciente não encontrado: " + id));
		paciente.setNmPaciente(pacienteDto.getNmPaciente());
		paciente.setPeso(pacienteDto.getPeso());
		paciente.setRaca(pacienteDto.getRaca());

		Paciente pacienteUpdate = repo.save(paciente);
		return pacienteMapper.toDto(pacienteUpdate);

	}

	public PacienteDto createPaciente(PacienteDto pacienteDto) {
		Paciente paciente = pacienteMapper.toEntity(pacienteDto);

		Paciente savedPaciente = repo.save(paciente);

		return pacienteMapper.toDto(savedPaciente);

	}

	public void deletePaciente(Long id) {
		repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Paciente não encontrado: " + id));
		repo.deleteById(id);
	}
}
