package com.registration.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.registration.dto.UserRegistrationDto;
import com.registration.entities.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}