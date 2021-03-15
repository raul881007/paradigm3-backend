package com.paradigm.main.rest;

import java.net.URI;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paradigm.main.model.Personne;
import com.paradigm.main.service.PersonneService;


/**
 * Classe qui contient les services rest et tous les itinéraires 
 * 
 * nécessaires pour effectuer des opérations sur des personnes
 * 
 *  @author Raul Herrera 
 * **/
@RestController
@RequestMapping("/personne/")
public class PersonneRest {
	
	/**
	 * Attribut pour accéder à le service et faire les actions
	 * **/
	@Autowired
	private PersonneService personneService;
	
	/**
	 * Methode pour obtenir les personnes
	 * @return List<Personnes> liste des personnes existantes dans la base de données avec un HttpStatus.OK 
	 * **/
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	private List<Personne> getAllPersonne(){
		return personneService.findAll();
	}
	
	/**
	 * Methode pour creer les personnes
	 * @param Personne personne qui aura dans le body la personne qui sera ajoutée 
	 * @return ResponseEntity.created avec la personne qui a été créée ou un HttpStatus.BAD_REQUEST au cas d'erreur  
	 * **/
	@PostMapping
	private ResponseEntity<Personne> createPersonne(@RequestBody Personne personne){
		
		try {
			Personne nPersonne=personneService.save(personne);
			return ResponseEntity.created(new URI("/personne/" +personne.getId())).body(personne);
			
		}catch (URISyntaxException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			// TODO: handle exception
		}
	}
	
	/**
	 * Methode pour actualizer les personnes
	 * @param Personne personne la personne qui sera mise à jour
	 * @param Long id id de la personne qui sera mise à jour
	 * @return Personne personne personne mise à jour avec ses nouvelles données dans HttpStatus.OK  
	 * **/
	@PutMapping(value="{id}")
	@ResponseStatus(HttpStatus.OK)
	private Personne updatePersonne(@RequestBody Personne personne,@PathVariable("id") Long id) {
		return personneService.findById(id)
				.map(mPersonne->{
				mPersonne.setPrenom(personne.getPrenom());
				return personneService.save(mPersonne);
				}).orElseGet(()->{
					personne.setId(id);
					return personneService.save(personne);
				});
				
		
	}
	
	/**
	 * Methode pour supprimer les personnes
	 * @param Long id id de la personne à eliminer dans la methode
	 * @return HttpStatus.OK avec un boolean  
	 * **/
	
	@DeleteMapping(value="{id}")
	@ResponseStatus(HttpStatus.OK)
	private ResponseEntity<Boolean> deletePersonne(@PathVariable("id") Long id){
		personneService.deleteById(id);
		return ResponseEntity.ok(personneService.findById(id)!=null);
	}

}
