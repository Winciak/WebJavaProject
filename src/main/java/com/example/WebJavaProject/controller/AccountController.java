package com.example.WebJavaProject.controller;

import com.example.WebJavaProject.dto.CustomUserDetails;
import com.example.WebJavaProject.dto.DtoUser;
import com.example.WebJavaProject.entity.User;
import com.example.WebJavaProject.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class AccountController {

    private UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    public String viewAccount(@AuthenticationPrincipal UserDetails loggedUser, Model theModel){

        String login = loggedUser.getUsername();
        User user = userService.findByLogin(login);
        DtoUser dtoUser = new DtoUser();
        dtoUser.setLogin(user.getLogin());
        dtoUser.setFirstName(user.getFirstName());
        dtoUser.setLastName(user.getLastName());
        dtoUser.setAge(user.getAge());

        theModel.addAttribute("dtoUser",dtoUser);


        return "users/account-page";
    }

    @PostMapping("/account/update")
    public String processRegistrationForm(
            @AuthenticationPrincipal CustomUserDetails loggedUser,
            @Valid @ModelAttribute("dtoUser") DtoUser dtoUser,
            BindingResult theBindingResult,
            Model theModel) {

        String login = loggedUser.getUsername();
        User user = userService.findByLogin(login);

        String dtoUserLogin = dtoUser.getLogin();

        System.out.println("pass: " + dtoUser.getPassword());


        // validation
        if (theBindingResult.hasErrors()){
            if(dtoUser.getPassword().isEmpty()){
                theModel.addAttribute("passwordError","Enter your password or new password");
            }
            return "users/account-page";
        }

        // checking if user changed his login
        if (!Objects.equals(login, dtoUserLogin)) {
            // checking if user already exists
            User existing = userService.findByLogin(dtoUserLogin);
            if (existing != null ){
                theModel.addAttribute("editError", "Login already exists.");

                return "users/account-page";
            }
        }

        loggedUser.setUsername(dtoUserLogin);



        userService.update(dtoUser,user);


        return "index";
    }
}
