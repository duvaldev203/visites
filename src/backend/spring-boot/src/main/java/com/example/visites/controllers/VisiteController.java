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
	@PreAuthorize("hasAnyAuthority('ADMIN', 'RECEP')")
	public ResponseEntity<List<VisiteResponse>> index(){
		return visiteService.index();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'RECEP', 'USER')")
	public ResponseEntity<VisiteResponse> show(@PathVariable Long id) {
		return visiteService.show(id);
	}

	@PostMapping("/")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'RECEP')")
	public ResponseEntity<VisiteResponse> create(@Valid @RequestBody VisiteRequest visite) {
		return visiteService.create(visite);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'RECEP')")
	public ResponseEntity<VisiteResponse> update(@PathVariable Long id, @Valid @RequestBody VisiteRequest visite) {
		return visiteService.update(visite, id);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return visiteService.delete(id);
	}

	@GetMapping("/records/{search}")
	public  ResponseEntity<List<VisiteResponse>> records(@PathVariable String search){
		return visiteService.records(search);
	}

	@GetMapping("/sort/{motif}")
	public ResponseEntity<List<VisiteResponse>> sort(@PathVariable String motif){
		return visiteService.sort(motif);
	}

	@GetMapping("/employe/{employeId}")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'RECEP', 'USER')")
	public ResponseEntity<List<VisiteResponse>> getVisiteByEmployeId(Long employeId) {
		return visiteService.getVisiteByEmployeId(employeId);
	}

}