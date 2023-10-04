package com.personregistrationspring.repository;

import com.personregistrationspring.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional; 

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findById(int roleId);
}