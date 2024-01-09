package com.taskmanagement.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.dto.request.AuthenticationRequest;
import com.taskmanagement.dto.response.AuthenticationResponse;
import com.taskmanagement.model.User;
import com.taskmanagement.repository.UserRepo;
import com.taskmanagement.security.jwt.JwtUtils;
import com.taskmanagement.security.services.UserDetailsImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	private UUID uuid;

	@PostMapping("/signin")
	public ResponseEntity<AuthenticationResponse> authenticateUser(
			@Validated @RequestBody AuthenticationRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getId(), userDetails.getEmail()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody AuthenticationRequest signUpRequest) {
		System.out.println(signUpRequest.getEmail());
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body("Error: Email already exists!");
		}
		
		System.out.println(signUpRequest.getEmail());

		// Create new user's account
		User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
		user.setID(uuid.toString());
		user.setTasks(new ArrayList<>());

		userRepository.save(user);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword()));

		String jwt = jwtUtils.generateJwtToken(authentication);

		return ResponseEntity.ok(new AuthenticationResponse(jwt, user.getID(), user.getEmail()));
	}
}