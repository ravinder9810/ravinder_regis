package com.registration.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.dto.UserRegistrationDto;
import com.registration.exception.UserEmailAlreadyExistException;
import com.registration.service.IUserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private IUserService userService;
	// constructor based injection 
	public UserRegistrationController(IUserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping()
	public String showRegistrationForm() {
		return "registration";    // here registration means it will goto registration.html 
	}
	
	@PostMapping()
	public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto registrationDto,BindingResult bindingResult) throws UserEmailAlreadyExistException{
		
		if(bindingResult.hasErrors())
		{
			return "registration";
		}
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}