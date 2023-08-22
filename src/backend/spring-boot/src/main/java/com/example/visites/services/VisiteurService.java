package com.example.visites.services;

import java.util.List;

import com.example.visites.dto.VisiteurRequest;
import com.example.visites.dto.VisiteurResponse;
import org.springframework.http.ResponseEntity;

public interface VisiteurService {

	ResponseEntity<List<VisiteurResponse>> index();

	ResponseEntity<VisiteurResponse> show(Long Id);

	ResponseEntity<VisiteurResponse> create(VisiteurRequest visiteur);

	ResponseEntity<VisiteurResponse> update(VisiteurRequest visiteur, Long id);

	ResponseEntity<?> delete(Long id);

}
