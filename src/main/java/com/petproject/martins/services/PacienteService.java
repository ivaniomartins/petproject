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
	public final PacienteMapper pacienteMapper = PacienteMapper.INSTANCE;

	public PacienteDto find(Long id) {

		Optional<Paciente> obj = repo.findById(id);
		return obj.map(pacienteMapper::toDto)
				.orElseThrow(() -> new ObjectNotFoundException(
						"Paciente não encontrado: " + id + ", Tipo: " + Paciente.class.getName()));

	}

	public List<PacienteDto> findAll() {

		return repo.findAll().stream()
				.map(pacienteMapper::toDto).collect(Collectors.toList());

	}

	public PacienteDto updatePaciente(Long id, PacienteDto pacienteDto) {
		Optional<Paciente> pacienteOptional = repo.findById(id);
		if (pacienteOptional.isPresent()) {
			Paciente paciente = pacienteOptional.get();
			paciente.setNmPaciente(pacienteDto.getNmPaciente());
			paciente.setPeso(pacienteDto.getPeso());
			paciente.setRaca(pacienteDto.getRaca());

			Paciente pacienteUpdate = repo.save(paciente);
			return pacienteMapper.toDto(pacienteUpdate);

		} else {
			return null;
		}

	}
}
