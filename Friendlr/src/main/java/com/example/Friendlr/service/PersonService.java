package com.example.Friendlr.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Friendlr.dto.PersonDto;
import com.example.Friendlr.entity.Person;
import com.example.Friendlr.mapper.PersonMapper;

@Service
public class PersonService {

	private static Long index = (long) 0;
	private Set<Person> persons;
	private PersonMapper personMapper;
	
	public PersonService(PersonMapper personMapper)
	{
		persons = new HashSet<Person>();
		this.personMapper = personMapper;
	}
	
	public Set<PersonDto> getPersons()
	{
		return persons.stream().map(personMapper::toPersonDto).collect(Collectors.toSet());
	}
	
	public PersonDto getPerson(Long id)
	{
		for (Person person : persons)
		{
			if (person.getId().equals(id))
			{
				return personMapper.toPersonDto(person);
			}
		}
		return null;
	}
	
	public PersonDto createPerson(PersonDto personDto)
	{
		if (personDto == null)
		{
			return null;
		}
		personDto.setId(index++);
		persons.add(personMapper.toPerson(personDto));
		return personDto;
	}

	public PersonDto editPerson(Long id, PersonDto personDto) {
		if (personDto == null)
		{
			return null;
		}
		
		for (Person p : persons)
		{
			if (p.getId().equals(id))
			{
				personDto.setId(id);
				persons.remove(p);
				persons.add(personMapper.toPerson(personDto));
				return personDto;
			}
		}
		
		return null;
	}

	public PersonDto deletePerson(Long id) {
		for (Person person : persons)
		{
			if (person.getId().equals(id))
			{
				persons.remove(person);
				return personMapper.toPersonDto(person);
			}
		}
		return null;
	}
}
