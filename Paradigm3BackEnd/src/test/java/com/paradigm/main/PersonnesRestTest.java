package com.paradigm.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paradigm.main.model.Personne;
import com.paradigm.main.rest.PersonneRest;
import com.paradigm.main.service.PersonneService;

@RunWith(SpringRunner.class)
@WebMvcTest(value= PersonneRest.class)
public class PersonnesRestTest {
	
	
	/**
	 * Préparer l'utilisation de 
	 * l'annotation @WebMvcTest avec la classe appropriée
	 * 
	 * **/
	@Autowired
	private MockMvc mockMvc;
	
	
	
	/**
	 * Create a mockBean from PersonneService for the access and test
	 * **/
	
	@MockBean
	private PersonneService sPersonneService;
	
	
	
	@Test
	public void testCreatePersonne() throws Exception{
		Personne personne = new Personne();
		
		personne.setId((long) 1);
		personne.setPrenom("TestPrenom");
		String jsonInput = this.mapToJson(personne);
		String url = "/personne/";
		
		Mockito.when(sPersonneService.save(Mockito.any(Personne.class))).thenReturn(personne);
		
		RequestBuilder resBuilder = MockMvcRequestBuilders
				.post(url)
				.accept(MediaType.APPLICATION_JSON).content(jsonInput);
		
		MvcResult result = mockMvc.perform(resBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String jsonOutput = response.getContentAsString();
		
		assertThat(jsonOutput).isEqualTo(jsonInput);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	public void testGetPersonnes() throws Exception{
		Personne p1 = new Personne();
		p1.setId((long) 1);
		p1.setPrenom("TestPrenom");
		
		Personne p2 = new Personne("Personne 2");
		p2.setId((long)2);
		
		
		List<Personne> listPersonnes = new ArrayList<>();
		listPersonnes.add(p1);
		listPersonnes.add(p2);
		
		String jsonExpected = this.mapToJson(listPersonnes);
		String url = "/personne/";
		
		
		
		Mockito.when(sPersonneService.findAll()).thenReturn(listPersonnes);
		
		RequestBuilder resBuilder = MockMvcRequestBuilders
				.get(url)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(resBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String jsonOutput = response.getContentAsString();
		
		assertThat(jsonOutput).isEqualTo(jsonExpected);
		
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	
}
