package com.personregistrationspring.repository;

import com.personregistrationspring.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
	Optional<Person> findById(int personId);
	
	@Query("SELECT p FROM Person p WHERE p.deleted = false ORDER BY p.GWA")
	List<Person> listPersonByGWA();
	
	@Query("SELECT p FROM Person p WHERE p.deleted = false ORDER BY p.dateHired")
	List<Person> listPersonByDateHired();
	
	@Query("SELECT p FROM Person p WHERE p.deleted = false ORDER BY p.name.lastName")
	List<Person> listPersonByLastName();
}