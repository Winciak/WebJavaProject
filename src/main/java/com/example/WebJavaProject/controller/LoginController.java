package com.example.WebJavaProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/home")
    public String showLanding() {

        return "index";

    }

    @GetMapping("/loginPage")
    public String showMyLoginPage() {

        return "login-page";

    }


    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";

    }

}