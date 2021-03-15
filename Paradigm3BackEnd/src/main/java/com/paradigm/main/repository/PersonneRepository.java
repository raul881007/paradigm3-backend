package com.paradigm.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paradigm.main.model.Personne;

/**
 * Classe qui connecte le projet à JPA (Java Persistance Api), 
 * prend en charge la persistance des données
 * 
 * @author Raul Herrera
 * 
 * **/
public interface PersonneRepository extends JpaRepository<Personne, Long>{

}
