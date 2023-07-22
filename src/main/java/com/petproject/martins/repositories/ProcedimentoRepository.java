package com.petproject.martins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petproject.martins.model.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long>{
	
	

}
