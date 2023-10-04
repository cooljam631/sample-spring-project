package com.personregistrationspring.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@ToString
public class Name{
	
	@Getter
	@Setter
	@Column(name = "first_name")
	private String firstName;
	
	@Getter
	@Setter
	@Column(name = "last_name")
	private String lastName;
	
	@Getter
	@Setter
	@Column(name = "middle_name")
	private String middleName;
	
	@Getter
	@Setter
	@Column(name = "suffix")
	private String suffix;
	
	@Getter
	@Setter
	@Column(name = "title")
	private String title;
}