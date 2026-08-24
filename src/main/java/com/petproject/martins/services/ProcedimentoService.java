package com.petproject.martins.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petproject.martins.model.Procedimento;
import com.petproject.martins.model.dto.ProcedimentoDto;
import com.petproject.martins.model.mapper.ProcedimentoMapper;
import com.petproject.martins.repositories.ProcedimentoRepository;
import com.petproject.martins.services.exceptions.ObjectNotFoundException;

@Service
public class ProcedimentoService {

	private final ProcedimentoRepository repo;
	private final ProcedimentoMapper procedimentoMapper = ProcedimentoMapper.INSTANCE;

	public ProcedimentoService(ProcedimentoRepository repo) {
		this.repo = repo;
	}

	@Transactional(readOnly = true)
	public ProcedimentoDto find(Long id) {
		Procedimento proc = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(
						"Procedimento não encontrado: " + id + ", Tipo: " + Procedimento.class.getName()));
		return procedimentoMapper.toDto(proc);
	}

	@Transactional(readOnly = true)
	public List<ProcedimentoDto> findAll() {
		return repo.findAll().stream()
				.map(procedimentoMapper::toDto)
				.collect(Collectors.toList());
	}

	@Transactional
	public ProcedimentoDto createProcedimento(ProcedimentoDto dto) {
		Procedimento entity = procedimentoMapper.toEntity(dto);
		Procedimento saved = repo.save(entity);
		return procedimentoMapper.toDto(saved);
	}

	@Transactional
	public ProcedimentoDto updateProcedimento(Long id, ProcedimentoDto dto) {
		Procedimento proc = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Procedimento não encontrado: " + id));
		proc.setNmProcedimento(dto.getNmProcedimento());
		proc.setVlProcedimento(dto.getVlProcedimento());
		Procedimento updated = repo.save(proc);
		return procedimentoMapper.toDto(updated);
	}

	@Transactional
	public void deleteProcedimento(Long id) {
		repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Procedimento não encontrado: " + id));
		repo.deleteById(id);
	}
}