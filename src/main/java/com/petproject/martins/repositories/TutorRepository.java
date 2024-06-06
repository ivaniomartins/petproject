package com.petproject.martins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petproject.martins.model.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

}
