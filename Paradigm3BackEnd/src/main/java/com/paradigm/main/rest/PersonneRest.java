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

@RestController
@RequestMapping("/personne/")
public class PersonneRest {
	
	@Autowired
	private PersonneService personneService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	private List<Personne> getAllPersonne(){
		return personneService.findAll();
	}
	
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
	
	@DeleteMapping(value="{id}")
	@ResponseStatus(HttpStatus.OK)
	private ResponseEntity<Boolean> deletePersonne(@PathVariable("id") Long id){
		personneService.deleteById(id);
		return ResponseEntity.ok(personneService.findById(id)==null);
	}

}
