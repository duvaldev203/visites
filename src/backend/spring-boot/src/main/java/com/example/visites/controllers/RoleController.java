package com.example.visites.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.visites.models.Role;
import com.example.visites.services.RoleService;
import com.example.visites.services.RoleServiceImpl;

@RestController
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleServiceImpl roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping
	public List<Role> index(){
		
		return roleService.index();
	}
	
	@GetMapping("/{id}")
	public Role show(@PathVariable Long id) {
		return roleService.show(id);
	}

	@PostMapping
	public Role create(@RequestBody Role role) {
		return roleService.create(role);
	}

	@PutMapping("/{id}")
	public Role update(@PathVariable Long id, @RequestBody Role role) {
		return roleService.update(role, id);
	}
	

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		roleService.delete(id);
	}

}