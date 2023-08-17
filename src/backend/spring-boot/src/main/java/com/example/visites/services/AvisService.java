package com.example.visites.services;

import java.util.List;

import com.example.visites.models.Avis;

public interface AvisService {
	
	List<Avis> index();
	
	Avis show(Long Id);
	
	Avis create(Avis avis);

	Avis update(Avis avis, Long id);
	
	void delete(Long id);
	
}
