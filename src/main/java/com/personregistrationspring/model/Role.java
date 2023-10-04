package com.personregistrationspring.model;

import com.personregistrationspring.model.RoleEnum;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Getter
	@Setter
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "name")
	@Getter
	@Setter
	private RoleEnum roleName;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	@Getter
	@Setter
	private Set<Person> persons;
}