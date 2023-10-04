package com.personregistrationspring.service;

import com.personregistrationspring.model.Person;
import com.personregistrationspring.model.dto.PersonDto;
import com.personregistrationspring.model.Role;
import com.personregistrationspring.model.Contact;

import java.util.List;

public interface PersonService{
	PersonDto createPerson(PersonDto personDto);
	PersonDto updatePerson(int personId, PersonDto personDto);
	void deletePerson(int personId);
	PersonDto getPersonById(int personId);
	List<Person> listPersonByGWA();
	List<Person> listPersonByDateHired();
	List<Person> listPersonByLastName();
	void addRoleToPerson(int personId, int roleId);
	void removeRoleFromPerson(int personId, int roleId);
	void addContactToPerson(int personId, Contact contact);
	void updateContactOfPerson(int personId, Contact newContact);
	void deleteContactOfPerson(int personId);
}
