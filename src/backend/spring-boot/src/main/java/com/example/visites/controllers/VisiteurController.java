package com.example.visites.controllers;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.visites.dto.VisiteurRequest;
import com.example.visites.dto.VisiteurResponse;
import com.example.visites.services.VisiteurService;
import com.example.visites.services.VisiteurServiceImpl;

@RestController
@RequestMapping("/visiteurs")
@PreAuthorize("hasAuthority('ADMIN')")
public class VisiteurController {

	private final VisiteurService visiteurService;

	@Autowired
	public VisiteurController(VisiteurServiceImpl visiteurService) {
		this.visiteurService = visiteurService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<VisiteurResponse>> index(){
		return visiteurService.index();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VisiteurResponse> show(@PathVariable Long id) {
		return visiteurService.show(id);
	}

	@PostMapping("/")
	public ResponseEntity<VisiteurResponse> create(@Valid @RequestBody VisiteurRequest visiteur) {
		return visiteurService.create(visiteur);
	}

	@PutMapping("/{id}")
	public ResponseEntity<VisiteurResponse> update(@PathVariable Long id, @Valid @RequestBody VisiteurRequest visiteur) {
		return visiteurService.update(visiteur, id);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return visiteurService.delete(id);
	}

	@GetMapping("/records/{search}")
	public ResponseEntity<List<VisiteurResponse>> records(@PathVariable String search){
		return visiteurService.records(search);
	}
	
}