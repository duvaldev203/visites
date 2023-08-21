package com.example.visites.services;

import java.util.List;

import com.example.visites.dto.AvisRequest;
import com.example.visites.dto.AvisResponse;

public interface AvisService {
	
	List<AvisResponse> index();
	
	AvisResponse show(Long Id);
	
	AvisResponse create(AvisRequest avis);

	AvisResponse update(AvisRequest avis, Long id);
	
	void delete(Long id);
	
}
