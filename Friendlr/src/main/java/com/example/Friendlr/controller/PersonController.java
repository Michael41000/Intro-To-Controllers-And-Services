package com.example.Friendlr.controller;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Friendlr.entity.Person;
import com.example.Friendlr.service.PersonService;

@RestController
@RequestMapping("person")
public class PersonController {
	
	private PersonService personService;
	
	public PersonController(PersonService personService)
	{
		this.personService = personService;
	}
	
	@GetMapping
	public Set<Person> getPersons()
	{
		return personService.getPersons();
	}
	
	@GetMapping("{id}")
	public Person getPerson(@PathVariable Long id, HttpServletResponse response)
	{
		Person person = personService.getPerson(id);
		
		if (person == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return person;
	}
	
	@PostMapping
	public Person createPerson(@RequestBody Person person, HttpServletResponse response)
	{
		Person createdPerson = personService.createPerson(person);
		if (createdPerson == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_CREATED);
		}
		return createdPerson;
	}
	
	@PutMapping("{id}")
	public Person editPerson(@PathVariable Long id, @RequestBody Person person, HttpServletResponse response)
	{
		Person editedPerson = personService.editPerson(id, person);
		if (editedPerson == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return editedPerson;
	}
	
	@DeleteMapping("{id}")
	public Person deletePerson(@PathVariable Long id, HttpServletResponse response)
	{
		Person deletedPerson = personService.deletePerson(id);
		if (deletedPerson == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return deletedPerson;
	}

}
