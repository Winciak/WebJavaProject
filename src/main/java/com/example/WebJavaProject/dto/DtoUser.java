package com.example.WebJavaProject.dto;

import com.example.WebJavaProject.validation.FieldMatch;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class DtoUser {

	@NotNull(message = "is required")
	@Pattern(regexp = "^[a-z]{3,20}", message = "only lowercase letters of length 3-20 allowed")
	private String login;

	@NotNull(message = "is required")
	@Size(min = 5, message = "has to be at least 5 characters/digit long")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 5, message = "has to be at least 5 characters/digit long")
	private String matchingPassword;

	@NotNull(message = "is required")
	@Pattern(regexp = "^[A-Z][A-Za-z]{2,19}", message = "first letter has to be uppercase, length 3-20 letters ")
	private String firstName;

	@NotNull(message = "is required")
	@Pattern(regexp = "^[A-Z][A-Za-z]{2,49}", message = "first letter has to be uppercase, length 3-50 letters ")
	private String lastName;

	@NotNull(message = "is required")
	@Min(value = 18, message = "you need to be at least 18 years old")
	private Integer age;

	public DtoUser() {

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
