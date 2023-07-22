package com.petproject.martins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petproject.martins.model.ItemAtendimento;

@Repository
public interface ItemAtendimentoRepository extends JpaRepository<ItemAtendimento, Long> {

}
