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
public class Address{
	
	@Getter
	@Setter
	@Column(name = "street_no")
	private String streetNo;
	
	@Getter
	@Setter
	@Column(name = "barangay")
	private String barangay;
	
	@Getter
	@Setter
	@Column(name = "city")
	private String city;
	
	@Getter
	@Setter
	@Column(name = "zip_code")
	private String zipCode;
}