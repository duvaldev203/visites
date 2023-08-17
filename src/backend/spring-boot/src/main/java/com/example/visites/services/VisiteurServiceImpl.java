package com.example.visites.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.visites.models.Visiteur;
import com.example.visites.repositories.VisiteurRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VisiteurServiceImpl implements VisiteurService {

	public final VisiteurRepository visiteurRepository;

	@Autowired
	public VisiteurServiceImpl(VisiteurRepository visiteurRepository) {
		this.visiteurRepository = visiteurRepository;
	}

	@Override
	public List<Visiteur> index() {
		return visiteurRepository.findAll();
	}

	@Override
	public Visiteur show(Long Id) {
		Optional<Visiteur> visiteur = visiteurRepository.findById(Id);
		if (visiteur.isPresent())
			return visiteur.get();
		throw new EntityNotFoundException("Le visiteur n'a pas ete trouve !!!");
	}

	@Override
	public Visiteur create(Visiteur visiteur) {
		visiteurRepository.save(visiteur);
		return visiteur;
	}

	@Override
	public Visiteur update(Visiteur visiteur, Long id) {
		Optional<Visiteur> optVisiteur = visiteurRepository.findById(id);
		if (optVisiteur.isPresent()) {
			Visiteur oldVisiteur = optVisiteur.get();
			oldVisiteur.setNom(visiteur.getNom());
			oldVisiteur.setPrenom(visiteur.getPrenom());
			oldVisiteur.setEmail(visiteur.getEmail());
			oldVisiteur.setDateNais(visiteur.getDateNais());
			oldVisiteur.setSexe(visiteur.getSexe());
			oldVisiteur.setTel(visiteur.getTel());
			oldVisiteur.setProfession(visiteur.getProfession());
			return visiteurRepository.save(oldVisiteur);
		}
		throw new EntityNotFoundException("Le visiteur a modifier n'a pas ete trouvee !!!");
	}

	@Override
	public void delete(Long id) {
		visiteurRepository.deleteById(id);
	}

}
