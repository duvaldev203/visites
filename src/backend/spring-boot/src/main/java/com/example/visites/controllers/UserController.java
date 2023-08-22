package com.example.visites.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.visites.dto.UserRequest;
import com.example.visites.dto.UserResponse;
import com.example.visites.services.UserService;
import com.example.visites.services.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserResponse>> index(){
		return userService.index();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> show(@PathVariable Long id) {
		return userService.show(id);
	}

	@PostMapping("/")
	public ResponseEntity<UserResponse> create(@RequestBody UserRequest user) {
		return userService.create(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest user) {
		return userService.update(user, id);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		 return userService.delete(id);
	}
	
}