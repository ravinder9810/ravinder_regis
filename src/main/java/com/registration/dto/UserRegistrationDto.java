package com.registration.dto;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.registration.entities.Role;
import com.registration.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data				// Data is from LAMBOK by using this no need to write setters and getters
@NoArgsConstructor // for every entity there should be an no argument constructor
@AllArgsConstructor	//@AllArgsConstructor is for Parameterized contructor 

public class UserRegistrationDto {
	
	@NotNull
	@Size(min = 4, message = "firstName shoud have mini 4 chars !")
	private String firstName;
	
	@NotNull
	@Size(min = 4, message = "secondName shoud have mini 4 chars !")
	private String lastName;
	
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",message = "emial should not matched pattern !!")
	private String email;
	
//	@NotNull
//	@Size(min = 8 ,message = " password should have minimum 8 characters ")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" ,message = "password should required credentials :")
	private String password;
	
	private Collection<Role> roles;

}