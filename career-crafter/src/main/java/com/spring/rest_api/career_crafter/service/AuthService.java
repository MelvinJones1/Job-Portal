package com.spring.rest_api.career_crafter.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.exception.InvalidUsernameException;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.AuthRepository;

@Service
public class AuthService {

	@Autowired
	private AuthRepository authRepository;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public User signUp(User user) throws InvalidUsernameException {
	    // Check if Username is unique 
	    User existingUser = authRepository.findByUsername(user.getUsername());
	    if (existingUser != null) { 
	        throw new InvalidUsernameException("Username already exists");
	    }

	    // Set default role if not provided
	    if (user.getRole() == null)
	        user.setRole(user.getRole());  // Set the role to INSTRUCTOR if not already set

	    // Encode the password
	    String encodedPass = bcrypt.encode(user.getPassword());
	    user.setPassword(encodedPass);

	    // Save and return the user
	    return authRepository.save(user);
	}

	public User findById (int userId)  throws InvalidIDException{
		Optional<User> optional = authRepository.findById(userId);
		if(optional.isEmpty()) {
			throw new InvalidIDException("User Id is not valid....");
		}
		return optional.get();
		
		}

	public User customerSignUp(User user) throws InvalidUsernameException {
	    // Check if Username is unique 
	    User existingUser = authRepository.findByUsername(user.getUsername());
	    if (existingUser != null) { 
	        throw new InvalidUsernameException("Username already exists");
	    }

	    // Set default role if not provided
	    if (user.getRole() == null)
	        user.setRole("ROLE_INSTRUCTOR");  // Set the role to INSTRUCTOR if not already set

	    // Encode the password
	    String encodedPass = bcrypt.encode(user.getPassword());
	    user.setPassword(encodedPass);

	    // Save and return the user
	    return authRepository.save(user);
	}
	

}