package com.example.visites.services;

import java.util.List;

import com.example.visites.models.Visiteur;

public interface VisiteurService {

	List<Visiteur> index();
	
	Visiteur show(Long Id);
	
	Visiteur create(Visiteur visiteur);

	Visiteur update(Visiteur visiteur, Long id);
	
	void delete(Long id);

}
