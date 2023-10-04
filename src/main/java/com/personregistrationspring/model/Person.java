package com.personregistrationspring.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Embedded;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "person")
public class Person{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Getter
	@Setter
	private int id;
	
	@Embedded
	@Getter
	@Setter
	private Name name;
	
	@Embedded
	@Getter
	@Setter
	private Address address;
	
	@Getter
	@Setter
	@Column(name = "birthday")
	private Date birthday;
	
	@Getter
	@Setter
	@Column(name = "GWA", columnDefinition = "DOUBLE PRECISION")
	private Double GWA;
	
	@Getter
	@Setter
	@Column(name = "date_hired")
	private Date dateHired;
	
	@Getter
	@Setter
	@Column(name = "currently_employed")
	private boolean currentlyEmployed;
	
	@Embedded
	@Getter
	@Setter
	private Contact contact;
	
	@Getter
	@Setter
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
	name = "person_role",
	joinColumns = @JoinColumn(name = "person_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@Getter
	@Setter
	@Column(name = "deleted")
	private boolean deleted;
	
}
