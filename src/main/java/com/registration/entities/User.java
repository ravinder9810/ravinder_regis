package com.registration.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data														// Data is from LAMBOK by using this no need to write setters and getters
@NoArgsConstructor 												// for every entity there should be an no argument constructor
@AllArgsConstructor											//@AllArgsConstructor is for Parameterized contructor 
@Entity														// email should be unique for that we are using unique constraints for email
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	
	@Id																// Id is to define that it is a primary key
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")							// if we want to give a differnt name from variable name we go with column annotation
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
																// if we use pattern then the below column(variable) has to follow that pattern otherwise it show user defined message
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",message = "emial should not matched pattern !!")
	private String email;

	
//	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" ,message = "password should contain minimun 8 characters :")
	private String password;
	

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn( name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id"))
	
	private Collection<Role> roles;
	
	//parameterized constructor
	public User(String firstName, String lastName, String email, String password,Collection<Role> role) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = role;
	}
	
	
	
	
}



