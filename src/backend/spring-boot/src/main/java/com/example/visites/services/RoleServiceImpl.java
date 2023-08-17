package com.example.visites.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.visites.models.Role;
import com.example.visites.repositories.RoleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

	public final RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Role> index() {
		return roleRepository.findAll();
	}

	@Override
	public Role show(Long Id) {
		Optional<Role> role = roleRepository.findById(Id);
		if (role.isPresent())
			return role.get();
		throw new EntityNotFoundException("Le role n'a pas ete trouve !!!");
	}

	@Override
	public Role create(Role role) {
		roleRepository.save(role);
		return role;
	}

	@Override
	public Role update(Role role, Long id) {
		Optional<Role> optRole = roleRepository.findById(id);
		if (optRole.isPresent()) {
			Role oldRole = optRole.get();
			oldRole.setNom(role.getNom());
			oldRole.setDescription(role.getDescription());
			oldRole.setUsers(role.getUsers());
			return roleRepository.save(oldRole);
		}
		throw new EntityNotFoundException("Le role a modifier n'a pas ete trouve !!!");
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
	}
	
	
}
