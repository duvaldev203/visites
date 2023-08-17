package com.example.visites.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.visites.models.Bureau;

public interface BureauService {
	
	ResponseEntity<List<Bureau>> index();
	
	ResponseEntity<Bureau> show(Long Id);
	
	ResponseEntity<Bureau> create(Bureau bureau);

	ResponseEntity<Bureau> update(Bureau bureau, Long id);
	
	ResponseEntity<?> delete(Long id);
	
}
