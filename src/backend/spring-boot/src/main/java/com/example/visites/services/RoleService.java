package com.example.visites.services;

import java.util.List;

import com.example.visites.models.Role;

public interface RoleService {

	
	List<Role> index();
	
	Role show(Long Id);
	
	Role create(Role role);

	Role update(Role role, Long id);
	
	void delete(Long id);

}
