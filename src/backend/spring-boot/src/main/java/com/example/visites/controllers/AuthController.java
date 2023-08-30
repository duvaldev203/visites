package com.example.visites.controllers;


import com.example.visites.dto.SignInRequest;
import com.example.visites.dto.UserRequest;
import com.example.visites.dto.UserResponse;
import com.example.visites.exceptions.UserNotFoundException;
import com.example.visites.manager.JWTUtil;
import com.example.visites.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping
@SecurityRequirement(name = "Tracking Application")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ModelMapper modelMapper;

	public AuthController() {
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UserRequest user) throws UserNotFoundException {

		modelMapper.map(userService.create(user), UserResponse.class);

		SignInRequest login = new SignInRequest(
				user.getEmail(), user.getPassword()
		);
		Map<String, Object> token = login(login);

		return new ResponseEntity<>(Collections.singletonMap("jwt-token", token),
				HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public Map<String, Object> login(@Valid @RequestBody SignInRequest request) {

		UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
				request.getEmail(), request.getPassword());

		authenticationManager.authenticate(authCredentials);

		String token = jwtUtil.generateToken(request.getEmail());

		return Collections.singletonMap("jwt-token", token);
	}
}