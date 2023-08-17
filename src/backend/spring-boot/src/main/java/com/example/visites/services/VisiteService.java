package com.example.visites.services;

import java.util.List;

import com.example.visites.models.Visite;

public interface VisiteService {
	
	List<Visite> index();
	
	Visite show(Long Id);
	
	Visite create(Visite visite);

	Visite update(Visite visite, Long id);
	
	void delete(Long id);

}
