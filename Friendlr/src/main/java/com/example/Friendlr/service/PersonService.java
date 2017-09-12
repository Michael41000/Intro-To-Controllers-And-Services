package com.example.Friendlr.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.Friendlr.entity.Person;

@Service
public class PersonService {

	private static Long index = (long) 0;
	private Set<Person> persons;
	
	public PersonService()
	{
		persons = new HashSet<Person>();
	}
	
	public Set<Person> getPersons()
	{
		return persons;
	}
	
	public Person getPerson(Long id)
	{
		for (Person person : persons)
		{
			if (person.getId().equals(id))
			{
				return person;
			}
		}
		return null;
	}
	
	public Person createPerson(Person person)
	{
		if (person == null)
		{
			return null;
		}
		person.setId(index++);
		persons.add(person);
		return person;
	}

	public Person editPerson(Long id, Person person) {
		if (person == null)
		{
			return null;
		}
		
		for (Person p : persons)
		{
			if (p.getId().equals(id))
			{
				person.setId(id);
				persons.remove(p);
				persons.add(person);
				return person;
			}
		}
		
		return null;
	}

	public Person deletePerson(Long id) {
		for (Person person : persons)
		{
			if (person.getId().equals(id))
			{
				persons.remove(person);
				return person;
			}
		}
		return null;
	}
}
