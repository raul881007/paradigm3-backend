package com.paradigm.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paradigm.main.model.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long>{

}
