package com.example.visites.services;

import java.util.List;

import com.example.visites.models.User;

public interface UserService {
	
	List<User> index();
	
	User show(Long Id);
	
	User create(User user);

	User update(User user, Long id);
	
	void delete(Long id);
	
}
