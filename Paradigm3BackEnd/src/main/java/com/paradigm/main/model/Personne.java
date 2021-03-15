package com.paradigm.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity model pour mapper les champs de la base de données
 * 
 * @author Raul Herrera 
 * **/

@Entity
@Table(name="personne")
public class Personne {

	/**
	 * clé primaire qui définit 
	 * une création consécutive 
	 * **/
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * Mapper le prenom 
	 * **/
	private String prenom;

	public Long getId() {
		return id;
	}

	/**
	 * Changer id 
	 * 
	 * @param id pour garder le nouveau id 
	 * 
	 * **/
	public void setId(Long id) {
		this.id = id;
	}


	public String getPrenom() {
		return prenom;
	}

	/**
	 * Changer prenom 
	 * 
	 * @param prenom pour garder le nouveau prenom 
	 * 
	 * **/
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Constructeur avec des paramètres de la classe 
	 * 
	 * @param prenom pour garder le nouveau prenom 
	 * 
	 * **/

	public Personne(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Constructeur de la classe 
	 * 
	 * **/
	public Personne() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
