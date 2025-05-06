package com.spring.rest_api.career_crafter.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.spring.rest_api.career_crafter.config.JwtUtil;
import com.spring.rest_api.career_crafter.dto.TokenDto;
import com.spring.rest_api.career_crafter.exception.InvalidUsernameException;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.service.AuthService;
import com.spring.rest_api.career_crafter.service.MyUserService;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = {"http://localhost:5173"}) // Allow frontend on localhost:5173 to interact with backend
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthService authService;

	@Autowired
	private MyUserService myUserService;

	@Autowired
	private JwtUtil jwtUtil;

	// Logger instance to capture activity for debugging and monitoring
	Logger logger = LoggerFactory.getLogger("AuthController");

	/**
	 * POST /signup - Register a new user
	 * Steps:
	 * 1. Accept User object from request body.
	 * 2. Pass to AuthService for validation and saving.
	 * 3. Log the attempt and return saved User object.
	 */
	@PostMapping("/signup")
	public User signUp(@RequestBody User user) throws InvalidUsernameException {
		logger.info("Signup is in progress for user: " + user.getUsername());
		return authService.signUp(user); // Delegate signup logic to service
	}

	/**
	 * POST /login - Get user details if already authenticated by Spring Security filter
	 * Steps:
	 * 1. Spring Security has authenticated the user; we receive the `Principal`.
	 * 2. Use `principal.getName()` to extract username.
	 * 3. Load and return user details using custom user service.
	 */
	@PostMapping("/login")
	public UserDetails login(Principal principal) {
		String username = principal.getName(); // Get the logged-in username
		logger.debug("Username given: " + username);
		return myUserService.loadUserByUsername(username); // Load user details by username
	}

	/**
	 * POST /token/generate - Authenticate and generate a JWT token
	 * Steps:
	 * 1. Accept username and password from request.
	 * 2. Create an Authentication token with them.
	 * 3. Use AuthenticationManager to verify credentials.
	 * 4. If valid, generate a JWT token.
	 * 5. Return token with metadata as a DTO.
	 */
	@PostMapping("/token/generate")
	public TokenDto generateToken(@RequestBody User user) {
		// Create an authentication token from the incoming username/password
		Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		
		// Authenticate using Spring Security
		authenticationManager.authenticate(auth);

		// Generate JWT token using the username
		String token = jwtUtil.generateToken(user.getUsername());

		// Prepare token response DTO
		TokenDto dto = new TokenDto();
		dto.setToken(token);
		dto.setUsername(user.getUsername());
		dto.setExpiry(jwtUtil.extractExpiration(token).toString());

		// Logging information
		logger.info("Token generated for User " + user.getUsername());
		logger.warn("Token will expire On " + dto.getExpiry());

		return dto; // Send token back to the client
	}

	/**
	 * GET /user/details - Retrieve logged-in user's details using JWT-based Principal
	 * Steps:
	 * 1. Extract username from authenticated Principal.
	 * 2. Load user details using the username.
	 * 3. Return user details to the frontend.
	 */
	@GetMapping("/user/details")
	public UserDetails getUserDetails(Principal principal) {
		String username = principal.getName(); // Extract username from Principal
		return myUserService.loadUserByUsername(username); // Return corresponding UserDetails
	}
}
