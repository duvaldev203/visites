package com.example.visites.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.visites.dto.UserRequest;
import com.example.visites.dto.UserResponse;
import com.example.visites.models.Role;
import com.example.visites.models.User;
import com.example.visites.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	

	@Override
	public ResponseEntity<List<UserResponse>> index() {
		List<UserResponse> users = userRepository.findAll()
				.stream().map(el->modelMapper.map(el, UserResponse.class))
				.collect(Collectors.toList());
		return  new ResponseEntity<>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponse> show(Long Id) {
		Optional<User> user = userRepository.findById(Id);
		if (user.isPresent())
			return new ResponseEntity<>(modelMapper.map(user.get(), UserResponse.class), HttpStatus.FOUND);
		throw new EntityNotFoundException("L'utilisateur n'a pas ete trouve !!!");
	}

	@Override
	public ResponseEntity<UserResponse> create(UserRequest user) {
		System.out.println(user);
		User newUser = modelMapper.map(user, User.class);
		List<Role> roles = new ArrayList<>();
		for (Role role : newUser.getRoles()) {
			roles.add(role);
		}
		newUser.setRoles(roles);
		UserResponse saved = modelMapper.map(userRepository.save(newUser), UserResponse.class);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserResponse> update(UserRequest user, Long id) {
		Optional<User> optUser = userRepository.findById(id);
		if (optUser.isPresent()) {
			User oldUser = modelMapper.map(optUser, User.class);
			oldUser.setId(id);
			UserResponse updated = modelMapper.map(userRepository.save(oldUser), UserResponse.class);
			return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
		}
		throw new EntityNotFoundException("L'utilisateur a modifier n'a pas ete trouve !!!");
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
