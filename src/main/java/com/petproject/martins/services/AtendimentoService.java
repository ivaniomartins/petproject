package com.petproject.martins.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petproject.martins.model.Atendimento;
import com.petproject.martins.model.Paciente;
import com.petproject.martins.model.dto.AtendimentoDto;
import com.petproject.martins.model.mapper.AtendimentoMapper;
import com.petproject.martins.repositories.AtendimentoRepository;
import com.petproject.martins.repositories.PacienteRepository;
import com.petproject.martins.services.exceptions.ObjectNotFoundException;

@Service
public class AtendimentoService {

	private final AtendimentoRepository repo;
	private final PacienteRepository pacienteRepo;
	private final AtendimentoMapper mapper = AtendimentoMapper.INSTANCE;

	public AtendimentoService(AtendimentoRepository repo, PacienteRepository pacienteRepo) {
		this.repo = repo;
		this.pacienteRepo = pacienteRepo;
	}

	@Transactional(readOnly = true)
	public AtendimentoDto find(Long id) {
		Atendimento obj = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(
						"Atendimento não encontrado: " + id + ", Tipo: " + Atendimento.class.getName()));
		return mapper.toDto(obj);
	}

	@Transactional(readOnly = true)
	public List<AtendimentoDto> findAll() {
		return repo.findAll().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Transactional
	public AtendimentoDto create(AtendimentoDto dto) {
		Paciente paciente = pacienteRepo.findById(dto.getPaciente().getCdPaciente())
				.orElseThrow(() -> new ObjectNotFoundException(
						"Paciente não encontrado: " + dto.getPaciente().getCdPaciente()));
		Atendimento entity = mapper.toEntity(dto);
		entity.setCdAtendimento(null);
		entity.setPaciente(paciente);
		Atendimento saved = repo.save(entity);
		return mapper.toDto(saved);
	}

	@Transactional
	public AtendimentoDto update(Long id, AtendimentoDto dto) {
		Atendimento entity = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Atendimento não encontrado: " + id));
		entity.setDtAtendimento(dto.getDtAtendimento());
		if (dto.getPaciente() != null && dto.getPaciente().getCdPaciente() != null) {
			Paciente paciente = pacienteRepo.findById(dto.getPaciente().getCdPaciente())
					.orElseThrow(() -> new ObjectNotFoundException(
							"Paciente não encontrado: " + dto.getPaciente().getCdPaciente()));
			entity.setPaciente(paciente);
		}
		Atendimento updated = repo.save(entity);
		return mapper.toDto(updated);
	}

	@Transactional
	public void delete(Long id) {
		repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Atendimento não encontrado: " + id));
		repo.deleteById(id);
	}
}