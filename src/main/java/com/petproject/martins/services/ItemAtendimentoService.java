package com.petproject.martins.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petproject.martins.model.Atendimento;
import com.petproject.martins.model.ItemAtendimento;
import com.petproject.martins.model.Procedimento;
import com.petproject.martins.model.dto.ItemAtendimentoDto;
import com.petproject.martins.model.mapper.ItemAtendimentoMapper;
import com.petproject.martins.repositories.AtendimentoRepository;
import com.petproject.martins.repositories.ItemAtendimentoRepository;
import com.petproject.martins.repositories.ProcedimentoRepository;
import com.petproject.martins.services.exceptions.ObjectNotFoundException;

@Service
public class ItemAtendimentoService {

	private final ItemAtendimentoRepository repo;
	private final AtendimentoRepository atendimentoRepo;
	private final ProcedimentoRepository procedimentoRepo;
	private final ItemAtendimentoMapper mapper = ItemAtendimentoMapper.INSTANCE;

	public ItemAtendimentoService(ItemAtendimentoRepository repo,
			AtendimentoRepository atendimentoRepo,
			ProcedimentoRepository procedimentoRepo) {
		this.repo = repo;
		this.atendimentoRepo = atendimentoRepo;
		this.procedimentoRepo = procedimentoRepo;
	}

	@Transactional(readOnly = true)
	public ItemAtendimentoDto find(Long id) {
		ItemAtendimento obj = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(
						"Item de atendimento não encontrado: " + id));
		return mapper.toDto(obj);
	}

	@Transactional(readOnly = true)
	public List<ItemAtendimentoDto> findAll() {
		return repo.findAll().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Transactional
	public ItemAtendimentoDto create(ItemAtendimentoDto dto) {
		Atendimento atendimento = atendimentoRepo.findById(dto.getAtendimento().getCdAtendimento())
				.orElseThrow(() -> new ObjectNotFoundException(
						"Atendimento não encontrado: " + dto.getAtendimento().getCdAtendimento()));
		Procedimento procedimento = procedimentoRepo.findById(dto.getProcedimento().getCdProcedimento())
				.orElseThrow(() -> new ObjectNotFoundException(
						"Procedimento não encontrado: " + dto.getProcedimento().getCdProcedimento()));

		ItemAtendimento entity = mapper.toEntity(dto);
		entity.setIdItem(null);
		entity.setAtendimento(atendimento);
		entity.setProcedimento(procedimento);
		ItemAtendimento saved = repo.save(entity);
		return mapper.toDto(saved);
	}

	@Transactional
	public ItemAtendimentoDto update(Long id, ItemAtendimentoDto dto) {
		ItemAtendimento entity = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Item de atendimento não encontrado: " + id));
		if (dto.getAtendimento() != null && dto.getAtendimento().getCdAtendimento() != null) {
			Atendimento atendimento = atendimentoRepo.findById(dto.getAtendimento().getCdAtendimento())
					.orElseThrow(() -> new ObjectNotFoundException(
							"Atendimento não encontrado: " + dto.getAtendimento().getCdAtendimento()));
			entity.setAtendimento(atendimento);
		}
		if (dto.getProcedimento() != null && dto.getProcedimento().getCdProcedimento() != null) {
			Procedimento procedimento = procedimentoRepo.findById(dto.getProcedimento().getCdProcedimento())
					.orElseThrow(() -> new ObjectNotFoundException(
							"Procedimento não encontrado: " + dto.getProcedimento().getCdProcedimento()));
			entity.setProcedimento(procedimento);
		}
		ItemAtendimento updated = repo.save(entity);
		return mapper.toDto(updated);
	}

	@Transactional
	public void delete(Long id) {
		repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Item de atendimento não encontrado: " + id));
		repo.deleteById(id);
	}
}