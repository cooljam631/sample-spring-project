package com.personregistrationspring.model.dto;

import com.personregistrationspring.model.Person;
import com.personregistrationspring.model.Name;
import com.personregistrationspring.model.Address;
import com.personregistrationspring.model.Contact;
import com.personregistrationspring.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;
import java.util.HashSet;

public class PersonDto{
	
	@Getter
	@Setter
	private int id;
	
	@Getter
	@Setter
	private String firstName;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	@Setter
	private String middleName;
	
	@Getter
	@Setter
	private String suffix;
	
	@Getter
	@Setter
	private String title;
	
	@Getter
	@Setter
	private String streetNo;
	
	@Getter
	@Setter
	private String barangay;
	
	@Getter
	@Setter
	private String city;
	
	@Getter
	@Setter
	private String zipCode;
	
	@Getter
	@Setter
	private Date birthday;
	
	@Getter
	@Setter
	private Double GWA;
	
	@Getter
	@Setter
	private Date dateHired;
	
	@Getter
	@Setter
	private boolean currentlyEmployed;
	
	@Getter
	@Setter
	private String landline;
	
	@Getter
	@Setter
	private String mobileNumber;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private Set<Role> roles;
	
	@Getter
	@Setter
	private Set<Integer> roleIds;
	
	@Getter
	@Setter
	private boolean deleted;	
}

