package com.personregistrationspring.service;

import com.personregistrationspring.model.Person;
import com.personregistrationspring.model.Name;
import com.personregistrationspring.model.Address;
import com.personregistrationspring.model.Contact;
import com.personregistrationspring.model.dto.PersonDto;
import com.personregistrationspring.model.Role;
import com.personregistrationspring.model.Contact;
import com.personregistrationspring.repository.PersonRepository;
import com.personregistrationspring.repository.RoleRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;

import java.util.Optional;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


@Service
public class PersonServiceImpl implements PersonService{
	
	private final PersonRepository personRepository;
	private final RoleRepository roleRepository;
	
	@Autowired
	public PersonServiceImpl(PersonRepository personRepository, RoleRepository roleRepository){
		this.personRepository = personRepository;
		this.roleRepository = roleRepository;
	}
	
	@Transactional
	@Override
	public PersonDto createPerson(PersonDto personDto){
		Person person = mapToEntity(personDto);
		
		Set<Integer> roleIds = personDto.getRoleIds();
		
		Set<Role> roles = new HashSet<>();
		
		for(Integer roleId : roleIds){
			Optional<Role> optionalRole = roleRepository.findById(roleId);
			if(optionalRole.isPresent()){
				Role role = optionalRole.get();
				roles.add(role);
			} else{
				throw new EntityNotFoundException("Role does not exist");
			}
		}
		person.setRoles(roles);
		Person createdPerson = personRepository.save(person);
		return mapToDto(createdPerson);
	}
	
	@Transactional
	@Override
	public PersonDto updatePerson(int personId, PersonDto personDto){
		Optional<Person> optionalPerson = personRepository.findById(personId);
		if(optionalPerson.isPresent()){
			Person personToUpdate = optionalPerson.get();
			Name updatedName = personToUpdate.getName() != null ? personToUpdate.getName() : new Name();
			Address updatedAddress = personToUpdate.getAddress() != null ? personToUpdate.getAddress() : new Address();
			Contact updatedContact = personToUpdate.getContact() != null ? personToUpdate.getContact() : new Contact();
			if(personDto.getFirstName() != null){
				updatedName.setFirstName(personDto.getFirstName());
			}
			
			if(personDto.getLastName() != null){
				updatedName.setLastName(personDto.getLastName());
			}
			
			if(personDto.getMiddleName() != null){
				updatedName.setMiddleName(personDto.getMiddleName());
			}
			
			if(personDto.getSuffix() != null){
				updatedName.setSuffix(personDto.getSuffix());
			}
			
			if(personDto.getTitle() != null){
				updatedName.setTitle(personDto.getTitle());
			}
			
			personToUpdate.setName(updatedName);
			
			if(personDto.getStreetNo() != null){
				updatedAddress.setStreetNo(personDto.getStreetNo());
			}
			
			if(personDto.getBarangay() != null){
				updatedAddress.setBarangay(personDto.getBarangay());
			}
			
			if(personDto.getCity() != null){
				updatedAddress.setCity(personDto.getCity());
			}
			
			if(personDto.getZipCode() != null){
				updatedAddress.setZipCode(personDto.getZipCode());
			}
			
			personToUpdate.setAddress(updatedAddress);
			
			if(personDto.getBirthday() != null){
				personToUpdate.setBirthday(personDto.getBirthday());
			}
			
			if(personDto.getGWA() != null){
				personToUpdate.setGWA(personDto.getGWA());
			}
			
			if(personDto.getDateHired() != null){
				personToUpdate.setDateHired(personDto.getDateHired());
			}
			
			personToUpdate.setCurrentlyEmployed(personDto.isCurrentlyEmployed());
			
			if(personDto.getLandline() != null){
				updatedContact.setLandline(personDto.getLandline());
			}
			
			if(personDto.getMobileNumber() != null){
				updatedContact.setMobileNumber(personDto.getMobileNumber());
			}
			
			if(personDto.getEmail() != null){
				updatedContact.setEmail(personDto.getEmail());
			}
			
			personToUpdate.setContact(updatedContact);
			Person updatedPerson = personRepository.save(personToUpdate);
			return mapToDto(updatedPerson);
		} else{
			throw new EntityNotFoundException("Person not found");
		}
		
	}
	@Transactional
	@Override
	public void deletePerson(int personId){
		Optional<Person> optionalPerson = personRepository.findById(personId);
		if(optionalPerson.isPresent()){
			Person person = optionalPerson.get();
			person.setDeleted(true);
			PersonDto personDto = mapToDto(person);
			updatePerson(personId, personDto);
		} else{
			throw new EntityNotFoundException("Person not found");
		}
	}
	
	@Override
	public PersonDto getPersonById(int personId){
		Optional<Person> optionalPerson = personRepository.findById(personId);
		if(optionalPerson.isPresent()){
			Person person = optionalPerson.get();
			PersonDto personDto = mapToDto(person);
			return personDto;
		} else {
			throw new EntityNotFoundException("Person not found");
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Person> listPersonByGWA(){
		return personRepository.listPersonByGWA();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Person> listPersonByDateHired(){
		return personRepository.listPersonByDateHired();
	}
	
	@Transactional(readOnly = true)
	@Override 
	public List<Person> listPersonByLastName(){
		return personRepository.listPersonByLastName();
	}
	
	@Transactional
	@Override
	public void addRoleToPerson(int personId, int roleId){
		Optional<Person> optionalPerson = personRepository.findById(personId);
		Optional<Role> optionalRole = roleRepository.findById(roleId);
		
		if(optionalPerson.isPresent() && optionalRole.isPresent()){
			Person person = optionalPerson.get();
			Role role = optionalRole.get();
			person.getRoles().add(role);
			personRepository.save(person);
		} else{
			throw new EntityNotFoundException("Person or Role not found");
		}
	}
	
	@Transactional
	@Override
	public void removeRoleFromPerson(int personId, int roleId){
		Optional<Person> optionalPerson = personRepository.findById(personId);
		Optional<Role> optionalRole = roleRepository.findById(roleId);
		
		if(optionalPerson.isPresent() && optionalRole.isPresent()){
			Person person = optionalPerson.get();
			Role role = optionalRole.get();
			person.getRoles().remove(role);
			personRepository.save(person);
		} else{
			throw new EntityNotFoundException("Person or Role not found");
		}
	}
	
	@Transactional
	@Override
	public void addContactToPerson(int personId, Contact contact){
		Optional<Person> optionalPerson = personRepository.findById(personId);
		if(optionalPerson.isPresent()){
			Person person = optionalPerson.get();
			if(person.getContact() == null){
				person.setContact(contact);
				personRepository.save(person);
			} else{
				throw new IllegalStateException("There is existing contact details for this person");
			}
		} else{
			throw new EntityNotFoundException("Person not found");
		}
	}
	
	@Transactional
	@Override
	public void updateContactOfPerson(int personId, Contact newContact){
		Optional<Person> optionalPerson = personRepository.findById(personId);
		
		if(optionalPerson.isPresent()){
			Person person = optionalPerson.get();
			if(person.getContact() != null){
				person.setContact(newContact);
				personRepository.save(person);
			} else{
				throw new IllegalStateException("Person does not have existing contact info");
			}
		} else{
			throw new EntityNotFoundException("Person not found");
		}
	}
	
	@Transactional
	@Override
	public void deleteContactOfPerson(int personId){
		Optional<Person> optionalPerson = personRepository.findById(personId);
		if(optionalPerson.isPresent()){
			Person person = optionalPerson.get();
			if(person.getContact() != null){
				person.setContact(null);
				personRepository.save(person);
			} else{
				throw new IllegalStateException("Person does not have any existing contact information");
			}
		} else{
			throw new EntityNotFoundException("Person not found");
		}
	}
	
	public Person mapToEntity(PersonDto personDto){
		Person person = new Person();
		person.setId(personDto.getId());
		Name name = new Name();
		name.setFirstName(personDto.getFirstName());
		name.setLastName(personDto.getLastName());
		name.setMiddleName(personDto.getMiddleName());
		name.setSuffix(personDto.getSuffix());
		name.setTitle(personDto.getTitle());
		person.setName(name);
		
		Address address = new Address();
		address.setStreetNo(personDto.getStreetNo());
		address.setBarangay(personDto.getBarangay());
		address.setCity(personDto.getCity());
		address.setZipCode(personDto.getZipCode());
		person.setAddress(address);
		
		person.setBirthday(personDto.getBirthday());
		person.setGWA(personDto.getGWA());
		person.setDateHired(personDto.getDateHired());
		person.setCurrentlyEmployed(personDto.isCurrentlyEmployed());
		
		Contact contact = new Contact();
		contact.setLandline(personDto.getLandline());
		contact.setMobileNumber(personDto.getMobileNumber());
		contact.setEmail(personDto.getEmail());
		person.setContact(contact);
		
		person.setRoles(personDto.getRoles());
		person.setDeleted(personDto.isDeleted());
		return person;
	}
	
	public PersonDto mapToDto(Person person){
		PersonDto dto = new PersonDto();
		dto.setId(person.getId());
		Name name = person.getName();
		dto.setFirstName(name.getFirstName());
		dto.setLastName(name.getLastName());
		dto.setMiddleName(name.getMiddleName());
		dto.setSuffix(name.getSuffix());
		dto.setTitle(name.getTitle());
		
		Address address = person.getAddress();
		dto.setStreetNo(address.getStreetNo());
		dto.setBarangay(address.getBarangay());
		dto.setCity(address.getCity());
		dto.setZipCode(address.getZipCode());
		
		dto.setBirthday(person.getBirthday());
		dto.setGWA(person.getGWA());
		dto.setDateHired(person.getDateHired());
		dto.setCurrentlyEmployed(person.isCurrentlyEmployed());
		
		Contact contact = person.getContact();
		dto.setLandline(contact.getLandline());
		dto.setMobileNumber(contact.getMobileNumber());
		dto.setEmail(contact.getEmail());
		
		dto.setRoles(person.getRoles());
		dto.setDeleted(person.isDeleted());
		return dto;
	}
}