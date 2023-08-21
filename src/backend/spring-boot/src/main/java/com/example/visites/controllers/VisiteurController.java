package com.example.visites.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class VisiteurController {

	private final VisiteurService visiteurService;

	@Autowired
	public VisiteurController(VisiteurServiceImpl visiteurService) {
		this.visiteurService = visiteurService;
	}
	
	@GetMapping("/")
	public List<VisiteurResponse> index(){
		return visiteurService.index();
	}
	
	@GetMapping("/{id}")
	public VisiteurResponse show(@PathVariable Long id) {
		return visiteurService.show(id);
	}

	@PostMapping("/")
	public VisiteurResponse create(@RequestBody VisiteurRequest visiteur) {
		return visiteurService.create(visiteur);
	}

	@PutMapping("/{id}")
	public VisiteurResponse update(@PathVariable Long id, @RequestBody VisiteurRequest visiteur) {
		return visiteurService.update(visiteur, id);
	}
	

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		visiteurService.delete(id);
	}
	
}