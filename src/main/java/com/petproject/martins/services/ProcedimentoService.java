package com.petproject.martins.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.martins.model.Procedimento;
import com.petproject.martins.model.dto.ProcedimentoDto;
import com.petproject.martins.model.mapper.ProcedimentoMapper;
import com.petproject.martins.repositories.ProcedimentoRepository;

@Service
public class ProcedimentoService {
	@Autowired
	ProcedimentoRepository repo;
	public final ProcedimentoMapper procedimentoMapper = ProcedimentoMapper.INSTANCE;

	public Procedimento find(Long id) {

		Optional<Procedimento> proc = repo.findById(id);

		return proc.orElse(null);

	}

	public List<ProcedimentoDto> findAll() {
		return repo.findAll().stream()
				.map(procedimentoMapper::toDto)
				.collect(Collectors.toList());

	}

	public ProcedimentoDto updateProcedimento(Long id, ProcedimentoDto procedimentoDto) {

		Optional<Procedimento> procedimentoOptional = repo.findById(id);
		if (procedimentoOptional.isPresent()) {
			Procedimento procedimento = procedimentoOptional.get();
			procedimento.setVlProcedimento(procedimentoDto.getVlProcedimento());

			Procedimento procedimentoUpdate = repo.save(procedimento);
			return procedimentoMapper.toDto(procedimentoUpdate);

		} else {
			return null;
		}
	}

	public boolean deleteProcedimento(Long id) {
		Optional<Procedimento> procedimento = repo.findById(id);
		if (procedimento.isPresent()) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
}
