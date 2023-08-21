package com.example.visites.services;

import java.util.List;

import com.example.visites.dto.RoleRequest;
import com.example.visites.dto.RoleResponse;

public interface RoleService {

	
	List<RoleResponse> index();
	
	RoleResponse show(Long Id);
	
	RoleResponse create(RoleRequest role);

	RoleResponse update(RoleRequest role, Long id);
	
	void delete(Long id);

}
