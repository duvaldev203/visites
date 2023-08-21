package com.example.visites.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.visites.dto.RoleRequest;
import com.example.visites.dto.RoleResponse;
import com.example.visites.models.Role;
import com.example.visites.repositories.RoleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;
	private ModelMapper modelMapper;
	
	public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<RoleResponse> index() {
		return roleRepository.findAll().stream().map(el->modelMapper.map(el, RoleResponse.class)).collect(Collectors.toList());
	}

	@Override
	public RoleResponse show(Long Id) {
		Optional<Role> role = roleRepository.findById(Id);
		if (role.isPresent())
			return modelMapper.map(role.get(), RoleResponse.class);
		throw new EntityNotFoundException("Le role n'a pas ete trouve !!!");
	}

	@Override
	public RoleResponse create(RoleRequest role) {
		Role newRole = modelMapper.map(role, Role.class);
		Role updated = roleRepository.save(newRole);
		return modelMapper.map(updated, RoleResponse.class);
	}

	@Override
	public RoleResponse update(RoleRequest role, Long id) {
		Optional<Role> optRole = roleRepository.findById(id);
		if (optRole.isPresent()) {
			Role oldRole = modelMapper.map(optRole, Role.class);
			oldRole.setId(id);
			Role updated = roleRepository.save(oldRole);
			return modelMapper.map(updated, RoleResponse.class);
		}
		throw new EntityNotFoundException("Le role a modifier n'a pas ete trouve !!!");
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
	}
	
	
}
