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

import com.example.visites.models.Bureau;
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
	
	@GetMapping
	public ResponseEntity<List<Bureau>> index(){
		return bureauService.index();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Bureau> show(@PathVariable Long id) {
		return bureauService.show(id);
	}

	@PostMapping
	public ResponseEntity<Bureau> create(@RequestBody Bureau bureau) {
		return bureauService.create(bureau);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Bureau> update(@PathVariable Long id, @RequestBody Bureau bureau) {
		return bureauService.update(bureau, id);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return bureauService.delete(id);
	}
}