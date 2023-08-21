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

import com.example.visites.dto.VisiteRequest;
import com.example.visites.dto.VisiteResponse;
import com.example.visites.services.VisiteService;
import com.example.visites.services.VisiteServiceImpl;

@RestController
@RequestMapping("/visites")
public class VisiteController {

	private final VisiteService visiteService;

	@Autowired
	public VisiteController(VisiteServiceImpl visiteService) {
		this.visiteService = visiteService;
	}
	
	@GetMapping("/")
	public List<VisiteResponse> index(){
		return visiteService.index();
	}
	
	@GetMapping("/{id}")
	public VisiteResponse show(@PathVariable Long id) {
		return visiteService.show(id);
	}

	@PostMapping("/")
	public VisiteResponse create(@RequestBody VisiteRequest visite) {
		return visiteService.create(visite);
	}

	@PutMapping("/{id}")
	public VisiteResponse update(@PathVariable Long id, @RequestBody VisiteRequest visite) {
		return visiteService.update(visite, id);
	}
	

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		visiteService.delete(id);
	}
	
}