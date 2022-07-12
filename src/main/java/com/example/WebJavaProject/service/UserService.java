package com.example.WebJavaProject.service;

import com.example.WebJavaProject.dto.DtoUser;
import com.example.WebJavaProject.entity.Role;
import com.example.WebJavaProject.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {

		 List<User> findAll();

	 	User findById(int theId);
	
	void save(User theUser);
	
	void deleteById(int theId);

		List<Role> findRoles();


		User findByLogin(String login);

	void save(DtoUser dtoUser);

    void update(DtoUser dtoUser, User user);
}
