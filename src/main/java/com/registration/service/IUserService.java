package com.registration.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.registration.dto.UserRegistrationDto;
import com.registration.entities.User;
import com.registration.exception.UserEmailAlreadyExistException;

public interface IUserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto) throws UserEmailAlreadyExistException;
}