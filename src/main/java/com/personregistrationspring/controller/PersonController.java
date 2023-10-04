package com.personregistrationspring.controller;

import com.personregistrationspring.model.Person;
import com.personregistrationspring.model.dto.PersonDto;
import com.personregistrationspring.model.Contact;
import com.personregistrationspring.service.PersonService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@RestController
@RequestMapping("/api/persons")
public class PersonController{
	
	private final PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService){
		this.personService = personService;
	}
	
	// @GetMapping("/current")
	// public List<String> getCurrentUserRoles() {
		// List<String> rolesList = new ArrayList<>();

		// Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// if (authentication != null && authentication.isAuthenticated()) {
			// Get the user's authorities (roles)
			// for (GrantedAuthority authority : authentication.getAuthorities()) {
				// rolesList.add(authority.getAuthority());
			// }
		// }

		// return rolesList;
	// }
	// @PostMapping("/login")
	// public ResponseEntity<Void> login() {

		// return ResponseEntity.ok().build();
	// }
	@PostMapping
	public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto){
		PersonDto createdPerson = personService.createPerson(personDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
	}
	
	@PutMapping("/{personId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PersonDto> updatePerson(@PathVariable int personId, @RequestBody PersonDto updatedPerson){
		try{
			PersonDto updated = personService.updatePerson(personId, updatedPerson);
			return ResponseEntity.ok(updated);
		} catch(EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/delete/{personId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deletePerson(@PathVariable int personId){
		PersonDto personToDelete = personService.getPersonById(personId);
		if(personToDelete == null){
			return ResponseEntity.notFound().build();
		}
		
		personService.deletePerson(personId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{personId}")
	public ResponseEntity<PersonDto> getPersonById(@PathVariable int personId){
		PersonDto personToFind = personService.getPersonById(personId);
		if(personToFind == null){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(personToFind);
	}
	
	@GetMapping("/gwa")
	public ResponseEntity<List<Person>> listPersonByGWA(){
		List<Person> persons = personService.listPersonByGWA();
		return ResponseEntity.ok(persons);
	}
	
	@GetMapping("/date-hired")
	public ResponseEntity<List<Person>> listPersonByDateHired(){
		List<Person> persons = personService.listPersonByDateHired();
		return ResponseEntity.ok(persons);
	}
	
	@GetMapping("/last-name")
	public ResponseEntity<List<Person>> listPersonByLastName(){
		List<Person> persons = personService.listPersonByLastName();
		return ResponseEntity.ok(persons);
	}
	
	@PostMapping("/{personId}/roles/{roleId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addRoleToPerson(@PathVariable int personId, @PathVariable int roleId){
		try{
			personService.addRoleToPerson(personId, roleId);
			return ResponseEntity.ok("Role added successfully");
		} catch(EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{personId}/roles/{roleId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> removeRoleFromPerson(@PathVariable int personId, @PathVariable int roleId){
		try{
			personService.removeRoleFromPerson(personId, roleId);
			return ResponseEntity.ok("Role removed successfully");
		} catch(EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{personId}/contact")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addContactToPerson(@PathVariable int personId, @RequestBody Contact contact){
		try{
			personService.addContactToPerson(personId, contact);
			return ResponseEntity.ok("Contact information added successfully");
		} catch(EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{personId}/contact")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateContactOfPerson(@PathVariable int personId, @RequestBody Contact newContact){
		try{
			personService.updateContactOfPerson(personId, newContact);
			return ResponseEntity.ok("Contact information updated successfully");
		} catch(EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{personId}/contact")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteContactOfPerson(@PathVariable int personId){
		try{
			personService.deleteContactOfPerson(personId);
			return ResponseEntity.ok("Contact Information deleted from Person successfully");
		} catch(EntityNotFoundException e){
			return ResponseEntity.notFound().build();
		}
	}

	 // @GetMapping("/admin")
	 // public String adminPage() {
		 // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 // if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
//			 The user has the ADMIN role, so allow them to access the page
			 // return "admin-page";
		 // } else {
//			 The user does not have the ADMIN role, so redirect them to the access denied page
			 // return "redirect:/access-denied";
		 // }
	 // }
}