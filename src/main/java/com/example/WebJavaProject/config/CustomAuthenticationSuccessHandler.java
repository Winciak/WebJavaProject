package com.example.WebJavaProject.config;

import com.example.WebJavaProject.entity.User;
import com.example.WebJavaProject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException  {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName();
		
		System.out.println("userName=" + userName);


		response.sendRedirect(request.getContextPath() + "/info/list");
	}

}
