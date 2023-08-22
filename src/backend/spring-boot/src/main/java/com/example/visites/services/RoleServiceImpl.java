package com.example.visites.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.visites.dto.RoleRequest;
import com.example.visites.dto.RoleResponse;
import com.example.visites.models.Role;
import com.example.visites.repositories.RoleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;
	private final ModelMapper modelMapper;
	
	public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<List<RoleResponse>> index() {
		List<RoleResponse> roles = roleRepository.findAll()
				.stream().map(el->modelMapper.map(el, RoleResponse.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RoleResponse> show(Long Id) {
		Optional<Role> role = roleRepository.findById(Id);
		if (role.isPresent())
			return new ResponseEntity<>(modelMapper.map(role.get(), RoleResponse.class),
					HttpStatus.FOUND);
		throw new EntityNotFoundException("Le role n'a pas ete trouve !!!");
	}

	@Override
	public ResponseEntity<RoleResponse> create(RoleRequest role) {
		Role newRole = modelMapper.map(role, Role.class);
		RoleResponse saved = modelMapper.map(roleRepository.save(newRole), RoleResponse.class);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RoleResponse> update(RoleRequest role, Long id) {
		Optional<Role> optRole = roleRepository.findById(id);
		if (optRole.isPresent()) {
			Role oldRole = modelMapper.map(optRole, Role.class);
			oldRole.setId(id);
			RoleResponse updated = modelMapper.map(roleRepository.save(oldRole), RoleResponse.class);
			return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
		}
		throw new EntityNotFoundException("Le role a modifier n'a pas ete trouve !!!");
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		roleRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
