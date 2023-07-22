package com.petproject.martins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petproject.martins.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
