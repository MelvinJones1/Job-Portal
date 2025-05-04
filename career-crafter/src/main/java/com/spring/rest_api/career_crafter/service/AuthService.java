package com.spring.rest_api.career_crafter.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.exception.InvalidUsernameException;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.AuthRepository;

@Service
public class AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	/**
	 * Signs up a new user after validating the username and password.
	 * 
	 * @param user The user object containing details for the new user
	 * @return The newly created User object after saving it to the repository
	 * @throws InvalidUsernameException If the username already exists in the
	 *                                  database
	 */
	public User signUp(User user) throws InvalidUsernameException {
		logger.info("Attempting to sign up new user: {}", user.getUsername());

		// Check if the username already exists in the repository
		User existingUser = authRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			// If user exists, throw an exception
			logger.warn("Username '{}' already exists.", user.getUsername());
			throw new InvalidUsernameException("Username already exists");
		}

		// If no role is provided, set the default role
		if (user.getRole() == null) {
			user.setRole("ROLE_USER"); // Default role for new users
		}

		// Encode the password before storing it
		String encodedPassword = bcrypt.encode(user.getPassword());
		user.setPassword(encodedPassword);

		// Save the user and return the saved object
		logger.info("Successfully signed up new user: {}", user.getUsername());
		return authRepository.save(user);
	}

	/**
	 * Retrieves a user by their ID.
	 * 
	 * @param userId The ID of the user to retrieve
	 * @return The user object if found
	 * @throws InvalidIDException If no user is found with the given ID
	 */
	public User findById(int userId) throws InvalidIDException {
		logger.info("Fetching user with ID: {}", userId);

		Optional<User> user = authRepository.findById(userId);
		if (user.isEmpty()) {
			logger.error("User with ID {} not found.", userId);
			throw new InvalidIDException("User ID is not valid.");
		}

		logger.info("User found: {}", user.get().getUsername());
		return user.get();
	}

	/**
	 * Signs up a new instructor, ensuring that the username is unique and that the
	 * instructor gets assigned a role of "ROLE_INSTRUCTOR" if no role is provided.
	 * 
	 * @param user The instructor object to be signed up
	 * @return The newly created instructor object
	 * @throws InvalidUsernameException If the username already exists in the
	 *                                  database
	 */
	public User instructorSignUp(User user) throws InvalidUsernameException {
		logger.info("Attempting to sign up new instructor: {}", user.getUsername());

		// Check if the username already exists in the repository
		User existingUser = authRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			// If user exists, throw an exception
			logger.warn("Username '{}' already exists for instructor.", user.getUsername());
			throw new InvalidUsernameException("Username already exists");
		}

		// Set the role to ROLE_INSTRUCTOR if not provided
		if (user.getRole() == null) {
			user.setRole("ROLE_INSTRUCTOR");
		}

		// Encode the password before storing it
		String encodedPassword = bcrypt.encode(user.getPassword());
		user.setPassword(encodedPassword);

		// Save the instructor and return the saved object
		logger.info("Successfully signed up new instructor: {}", user.getUsername());
		return authRepository.save(user);
	}
}
