package com.example.WebJavaProject.controller;


import com.example.WebJavaProject.dto.DtoUser;
import com.example.WebJavaProject.entity.User;
import com.example.WebJavaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;

    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationPage")
	public String showMyRegisterPage(Model theModel) {
		
		theModel.addAttribute("dtoUser", new DtoUser());
		
		return "registration-page";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("dtoUser") DtoUser dtoUser,
				BindingResult theBindingResult, 
				Model theModel) {
		
		String login = dtoUser.getLogin();

		
		 if (theBindingResult.hasErrors()){
			 return "registration-page";
	        }

        User existing = userService.findByLogin(login);
        if (existing != null){
			theModel.addAttribute("registrationError", "Login already exists.");

        	return "registration-page";
        }
        
        userService.save(dtoUser);
        

        return "registration-confirmation";		
	}
}
