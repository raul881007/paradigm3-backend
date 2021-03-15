package com.paradigm.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="personne")
public class Personne {

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String prenom;

	public Long getId() {
		return id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Personne(Long id, String prenom) {
		
		this.id = id;
		this.prenom = prenom;
	}

	public Personne() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
