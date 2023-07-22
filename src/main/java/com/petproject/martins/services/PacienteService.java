package com.petproject.martins.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.martins.model.Paciente;
import com.petproject.martins.repositories.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;

	public Paciente buscar(Long id) {

		Optional<Paciente> obj = repo.findById(id);

		return obj.orElse(null);

	}

	
	public List<Paciente> findAll(){
		
		return repo.findAll();
		
	}
	
	
	public Paciente update(Paciente obj) {
		// incluir a lógica de atualização com base no registro do banco de dados.
		Paciente newObj = buscar(obj.getCdPaciente());
		updatePaciente(newObj, obj);
		return repo.save(newObj);
	}
	
	public void  updatePaciente (Paciente newObj, Paciente obj) {
		
		obj.setNmPaciente(newObj.getNmPaciente());
		obj.setDtNascimento(newObj.getDtNascimento());
		obj.setPeso(newObj.getPeso());
		obj.setRaca(newObj.getRaca());
		obj.setTutor(newObj.getTutor());		 
		
	}
}
