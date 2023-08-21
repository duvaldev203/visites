package com.example.visites.services;

import java.util.List;

import com.example.visites.dto.UserRequest;
import com.example.visites.dto.UserResponse;

public interface UserService {
	
	List<UserResponse> index();
	
	UserResponse show(Long Id);
	
	UserResponse create(UserRequest user);

	UserResponse update(UserRequest user, Long id);
	
	void delete(Long id);
	
}
