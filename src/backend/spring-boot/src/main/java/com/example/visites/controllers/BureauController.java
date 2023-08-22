package com.example.visites.controllers;

import java.util.List;

import jakarta.validation.Valid;
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

import com.example.visites.dto.BureauRequest;
import com.example.visites.dto.BureauResponse;
import com.example.visites.services.BureauService;
import com.example.visites.services.BureauServiceImpl;

@RestController
@RequestMapping("/bureaux")
public class BureauController {

	private final BureauService bureauService;

	@Autowired
	public BureauController(BureauServiceImpl bureauService) {
		this.bureauService = bureauService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<BureauResponse>> index(){
		return bureauService.index();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BureauResponse> show(@PathVariable Long id) {
		return bureauService.show(id);
	}

	@PostMapping("/")
	public ResponseEntity<BureauResponse> create(@Valid @RequestBody BureauRequest bureau) {
		return bureauService.create(bureau);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BureauResponse> update(@PathVariable Long id, @Valid @RequestBody BureauRequest bureau) {
		return bureauService.update(bureau, id);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return bureauService.delete(id);
	}
}
