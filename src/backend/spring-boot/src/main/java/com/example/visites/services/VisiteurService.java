package com.example.visites.services;

import java.util.List;

import com.example.visites.dto.VisiteurRequest;
import com.example.visites.dto.VisiteurResponse;

public interface VisiteurService {

	List<VisiteurResponse> index();
	
	VisiteurResponse show(Long Id);
	
	VisiteurResponse create(VisiteurRequest visiteur);

	VisiteurResponse update(VisiteurRequest visiteur, Long id);
	
	void delete(Long id);

}
