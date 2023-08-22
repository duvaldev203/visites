package com.example.visites.services;

import java.util.List;

import com.example.visites.dto.VisiteRequest;
import com.example.visites.dto.VisiteResponse;
import org.springframework.http.ResponseEntity;

public interface VisiteService {
	
	ResponseEntity<List<VisiteResponse>> index();
	
	ResponseEntity<VisiteResponse> show(Long Id);
	
	ResponseEntity<VisiteResponse> create(VisiteRequest visite);

	ResponseEntity<VisiteResponse> update(VisiteRequest visite, Long id);
	
	ResponseEntity<?> delete(Long id);

}
