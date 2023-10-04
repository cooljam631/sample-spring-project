package com.personregistrationspring.model;


public enum RoleEnum{
		ADMIN,
		DEV,
		QA,
		BA;
		
	public static RoleEnum fromString(String value){
		for(RoleEnum role : RoleEnum.values()){
			if(role.toString().equalsIgnoreCase(value)){
				return role;
			}
		}
		return null;
	}
}