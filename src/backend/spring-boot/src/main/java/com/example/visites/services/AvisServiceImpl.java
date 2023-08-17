package com.example.visites.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.visites.models.Avis;
import com.example.visites.repositories.AvisRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AvisServiceImpl implements AvisService {
	
	public final AvisRepository avisRepository;

	@Autowired
	public AvisServiceImpl(AvisRepository avisRepository) {
		this.avisRepository = avisRepository;
	}

	@Override
	public List<Avis> index() {
		return avisRepository.findAll();
	}

	@Override
	public Avis show(Long Id) {
		Optional<Avis> avis = avisRepository.findById(Id);
		if (avis.isPresent())
			return avis.get();
		throw new EntityNotFoundException("L'avis n'a pas ete trouve");
	}

	@Override
	public Avis create(Avis avis) {
		avisRepository.save(avis);
		return avis;
	}

	@Override
	public Avis update(Avis avis, Long id) {
		Optional<Avis> optAvis = avisRepository.findById(id);
		if (optAvis.isPresent()) {
			Avis oldAvis = optAvis.get();
			oldAvis.setLibelle(avis.getLibelle());
			oldAvis.setVisite(avis.getVisite());
			return avisRepository.save(oldAvis);
		}
		throw new EntityNotFoundException("L'avis a modifier n'a pas ete trouve !!!");
	}

	@Override
	public void delete(Long id) {
		avisRepository.deleteById(id);
	}

}
