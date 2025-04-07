package com.spring.rest_api.career_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.AuthRepository;

@Service
public class AuthService {

	@Autowired
	private AuthRepository authRepository;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public User signUp(User user) {
		if(user.getRole() == null)
			user.setRole("USER_DEFAULT");
		
		//encode the password 
		String encodedPass = bcrypt.encode(user.getPassword());
		
		//attach encoded pass to user 
		user.setPassword(encodedPass);
		
		return authRepository.save(user);
	}

}