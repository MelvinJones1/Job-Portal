package com.spring.rest_api.career_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.AuthRepository;

@Service
public class MyUserService implements UserDetailsService {

	@Autowired
	private AuthRepository authRepository;

	// Logger for logging events
	private static final Logger logger = LoggerFactory.getLogger(MyUserService.class);

	/**
	 * Load user by username from the database and check username validity.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Attempting to load user by username: {}", username);

		// Fetch the user by username
		User user = authRepository.findByUsername(username);

		// If user is not found, log and throw exception
		if (user == null) {
			logger.error("User not found: {}", username);
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		// Ensure case-insensitive username match
		if (!user.getUsername().equalsIgnoreCase(username)) {
			logger.error("Username mismatch: Token Username = {}, DB Username = {}", username, user.getUsername());
			throw new UsernameNotFoundException("Username mismatch: " + username);
		}

		logger.info("User found: {}", username);
		return user;
	}
}
