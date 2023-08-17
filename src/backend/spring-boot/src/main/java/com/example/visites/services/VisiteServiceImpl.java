package com.example.visites.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.visites.models.Visite;
import com.example.visites.repositories.VisiteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VisiteServiceImpl implements VisiteService {
	
	public final VisiteRepository visiteRepository;

	@Autowired
	public VisiteServiceImpl(VisiteRepository visiteRepository) {
		this.visiteRepository = visiteRepository;
	}
	@Override
	public List<Visite> index() {
		return visiteRepository.findAll();
	}

	@Override
	public Visite show(Long Id) {
		Optional<Visite> visite = visiteRepository.findById(Id);
		if (visite.isPresent())
			return visite.get();
		throw new EntityNotFoundException("La visite n'a pas ete trouve !!!");
	}

	@Override
	public Visite create(Visite visite) {
		visiteRepository.save(visite);
		return visite;
	}

	@Override
	public Visite update(Visite visite, Long id) {
		Optional<Visite> optVisite = visiteRepository.findById(id);
		if (optVisite.isPresent()) {
			Visite oldVisite = optVisite.get();
			oldVisite.setMotif(visite.getMotif());
			oldVisite.setDateVisite(visite.getDateVisite());
			oldVisite.setHeureDebut(visite.getHeureDebut());
			oldVisite.setHeureFin(visite.getHeureFin());
			oldVisite.setType(visite.getType());
			oldVisite.setVisiteur(visite.getVisiteur());
			oldVisite.setUser(visite.getUser());
			return visiteRepository.save(oldVisite);
		}
		throw new EntityNotFoundException("La visite a modifier n'a pas ete trouvee !!!");
	}

	@Override
	public void delete(Long id) {
		visiteRepository.deleteById(id);
	}
	
}
