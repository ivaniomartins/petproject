package com.petproject.martins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petproject.martins.model.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

}
