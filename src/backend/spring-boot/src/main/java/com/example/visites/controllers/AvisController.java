package com.example.visites.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.visites.dto.AvisRequest;
import com.example.visites.dto.AvisResponse;
import com.example.visites.services.AvisService;
import com.example.visites.services.AvisServiceImpl;

@RestController
@RequestMapping("/avis")
public class AvisController {

	private final AvisService avisService;

	@Autowired
	public AvisController(AvisServiceImpl avisService) {
		this.avisService = avisService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<AvisResponse>> index(){
		return avisService.index();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AvisResponse> show(@PathVariable Long id) {
		return avisService.show(id);
	}

	@PostMapping("/")
	public ResponseEntity<AvisResponse> create(@RequestBody AvisRequest avis) {
		return avisService.create(avis);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AvisResponse> update(@PathVariable Long id, @RequestBody AvisRequest avis) {
		return avisService.update(avis, id);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return avisService.delete(id);
    }
	
}
