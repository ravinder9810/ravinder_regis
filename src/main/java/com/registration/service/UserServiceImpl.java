package com.registration.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.registration.dto.UserRegistrationDto;
import com.registration.entities.Role;
import com.registration.entities.User;
import com.registration.exception.UserEmailAlreadyExistException;
import com.registration.repository.UserRepository;


@Service
public class UserServiceImpl implements IUserService{
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired									//	here I am injecting UserRepository because i am going to use methods of JPARepository to save data for registration
	private UserRepository userRepository;
	
	@Autowired 								//	here I am injecting BCryptPasswordEncoder because i am going to use password encrypting
	private BCryptPasswordEncoder passwordEncoder;
	


	@Override													// throwing exception if email already exist 
	public User register(UserRegistrationDto registrationDto) throws UserEmailAlreadyExistException
	{
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		
			if(checkIfUserExist(user.getEmail()))
			{
				logger.info(" user exist the Mail-Id ");
				logger.info(" please try with another Mail-Id ");				
				throw new UserEmailAlreadyExistException();
				
			}
			
			logger.info(" you had successfully registered  ");
			return userRepository.save(user);
	}

	@Override	 // loadByUserName is an method from UserDetailsService Interface is used to find the user exist with userNAme or Not 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			logger.info("invalid userName or password");
			throw new UsernameNotFoundException("Invalid  userName or password.");
		}
	return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));				
	}
	

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
	{
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	public boolean checkIfUserExist(String email)
	{
		return userRepository.findByEmail(email) != null ? true : false;
		}

	
}

	
