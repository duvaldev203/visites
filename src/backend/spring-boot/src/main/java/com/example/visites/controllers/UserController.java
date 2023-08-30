package com.example.visites.controllers;

import java.io.IOException;
import java.util.List;

import com.example.visites.dto.ProfileResponse;
import com.example.visites.exceptions.APIException;
import com.example.visites.models.Profile;
import com.example.visites.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.visites.dto.UserRequest;
import com.example.visites.dto.UserResponse;
import com.example.visites.services.UserService;
import com.example.visites.services.UserServiceImpl;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

	private final UserService userService;
	private final ProfileService profileService;

	@Autowired
	public UserController(UserServiceImpl userService, ProfileService profileService) {
		this.userService = userService;
		this.profileService = profileService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserResponse>> index(){
		return userService.index();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> show(@PathVariable Long id) {
		return userService.show(id);
	}

	@PostMapping("/")
	public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest user) {
		//Attribution de l'image par defaut
		user.setProfile(profileService.setDefaultProfile(user.getEmail()).getBody());
		return userService.create(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserRequest user, @RequestParam("image")MultipartFile image) {
		if (!image.isEmpty()) {
			ProfileResponse profile = profileService.saveProfile(image).getBody();
			user.setProfile(profile);
		}
		return userService.update(user, id);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		 return userService.delete(id);
	}

	@GetMapping("/records/{search}")
	public ResponseEntity<List<UserResponse>> records(@PathVariable String search){
		return userService.records(search);
	}

	@PostMapping("/profile")
	public ResponseEntity<ProfileResponse> saveImage(@RequestParam("image") MultipartFile image){
		return profileService.saveProfile(image);
	}

	@GetMapping("/profile/{profileId}")
	public ResponseEntity<byte[]> getProfileImage(@PathVariable Long profileId){
		return profileService.getProfileImage(profileId);
	}

	@PutMapping("profile/{id}")
	public ResponseEntity<ProfileResponse> changeProfile(@PathVariable Long id, @RequestParam("image") MultipartFile image){
		return profileService.changeProfileImage(id, image);
	}
	
}