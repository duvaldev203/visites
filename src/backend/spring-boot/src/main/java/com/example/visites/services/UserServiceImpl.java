package com.example.visites.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	private ModelMapper modelMapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	

	@Override
	public List<UserResponse> index() {
		return userRepository.findAll()
				.stream().map(el->modelMapper.map(el, UserResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserResponse show(Long Id) {
		Optional<User> user = userRepository.findById(Id);
		if (user.isPresent())
			return modelMapper.map(user.get(), UserResponse.class);
		throw new EntityNotFoundException("L'utilisateur n'a pas ete trouve !!!");
	}

	@Override
	public UserResponse create(UserRequest user) {
		System.out.println(user);
		User newUser = modelMapper.map(user, User.class);
		List<Role> roles = new ArrayList<>();
		for (Role role : newUser.getRoles()) {
			roles.add(role);
		}
		newUser.setRoles(roles);
		User saved = userRepository.save(newUser);
		return modelMapper.map(saved, UserResponse.class);
	}

	@Override
	public UserResponse update(UserRequest user, Long id) {
		Optional<User> optUser = userRepository.findById(id);
		if (optUser.isPresent()) {
			User oldUser = modelMapper.map(optUser, User.class);
			oldUser.setId(id);
			User updated = userRepository.save(oldUser);
			return modelMapper.map(updated, UserResponse.class);
		}
		throw new EntityNotFoundException("L'utilisateur a modifier n'a pas ete trouve !!!");
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
