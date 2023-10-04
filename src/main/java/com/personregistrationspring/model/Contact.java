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
public class Contact{
	
	@Getter
	@Setter
	@Column(name = "landline")
	private String landline;
	
	@Getter
	@Setter
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Getter
	@Setter
	@Column(name = "email")
	private String email;
}