package com.registration.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.dto.UserRegistrationDto;
import com.registration.entities.User;
import com.registration.exception.UserEmailAlreadyExistException;
import com.registration.service.IUserService;

@Controller								//@Controller is a common annotation that is used to mark a class as Spring MVC Controller
@RequestMapping("/registration")		//RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
										//@RequestMapping can be applied to the controller class as well as methods.
public class UserRegistrationController {

	private IUserService userService;
										// constructor based injection 
	public UserRegistrationController(IUserService userService) {
		super();
		this.userService = userService;
	}
	
//	@ModelAttribute("user")
//    public UserRegistrationDto userRegistrationDto() {
//        return new UserRegistrationDto();
//    }
	
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping()
	public String showRegistrationForm() {
		return "registration";    		// here registration means it will goto registration.html 
	}
										//@ModelAttribute is an annotation that binds a method parameter or method return value to a named model attribute and then exposes it to a web view.
	@PostMapping()						// Binding Result : that holds the result of the validation and binding and contains errors that may have occurred. 
	public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto registrationDto,BindingResult bindingResult) throws UserEmailAlreadyExistException{
		
		if(bindingResult.hasErrors())
		{
			return "registration";
		}
		
		userService.register(registrationDto);
		return "redirect:/registration?success";
	}
}