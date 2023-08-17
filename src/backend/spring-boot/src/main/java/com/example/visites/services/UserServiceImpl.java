package com.example.visites.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.visites.models.User;
import com.example.visites.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	public final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	

	@Override
	public List<User> index() {
		return userRepository.findAll();
	}

	@Override
	public User show(Long Id) {
		Optional<User> user = userRepository.findById(Id);
		if (user.isPresent())
			return user.get();
		throw new EntityNotFoundException("L'utilisateur n'a pas ete trouve !!!");
	}

	@Override
	public User create(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public User update(User user, Long id) {
		Optional<User> optUser = userRepository.findById(id);
		if (optUser.isPresent()) {
			User oldUser = optUser.get();
			oldUser.setNom(user.getNom());
			oldUser.setPrenom(user.getPrenom());
			oldUser.setEmail(user.getEmail());
			oldUser.setDateNais(user.getDateNais());
			oldUser.setSexe(user.getSexe());
			oldUser.setTel(user.getTel());
			oldUser.setPoste(user.getPoste());
			oldUser.setUsername(user.getUsername());
			
			return userRepository.save(oldUser);
		}
		throw new EntityNotFoundException("L'utilisateur a modifier n'a pas ete trouve !!!");
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
