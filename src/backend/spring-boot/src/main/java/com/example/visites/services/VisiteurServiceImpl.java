package com.example.visites.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.visites.dto.VisiteurRequest;
import com.example.visites.dto.VisiteurResponse;
import com.example.visites.models.Visiteur;
import com.example.visites.repositories.VisiteurRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VisiteurServiceImpl implements VisiteurService {

	private final VisiteurRepository visiteurRepository;
	private ModelMapper modelMapper;

	@Autowired
	public VisiteurServiceImpl(VisiteurRepository visiteurRepository, ModelMapper modelMapper) {
		this.visiteurRepository = visiteurRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<VisiteurResponse> index() {
		return visiteurRepository.findAll()
				.stream().map(el->modelMapper.map(el, VisiteurResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public VisiteurResponse show(Long Id) {
		Optional<Visiteur> visiteur = visiteurRepository.findById(Id);
		if (visiteur.isPresent())
			return modelMapper.map(visiteur, VisiteurResponse.class);
		throw new EntityNotFoundException("Le visiteur n'a pas ete trouve !!!");
	}

	@Override
	public VisiteurResponse create(VisiteurRequest visiteur) {
		Visiteur newVisiteur = modelMapper.map(visiteur, Visiteur.class);
		Visiteur saved = visiteurRepository.save(newVisiteur);
		return modelMapper.map(saved, VisiteurResponse.class);
	}

	@Override
	public VisiteurResponse update(VisiteurRequest visiteur, Long id) {
		Optional<Visiteur> optVisiteur = visiteurRepository.findById(id);
		if (optVisiteur.isPresent()) {
			Visiteur oldVisiteur = modelMapper.map(optVisiteur, Visiteur.class);
			oldVisiteur.setId(id);
			Visiteur updated = visiteurRepository.save(oldVisiteur);
			return modelMapper.map(updated, VisiteurResponse.class);
		}
		throw new EntityNotFoundException("Le visiteur a modifier n'a pas ete trouvee !!!");
	}

	@Override
	public void delete(Long id) {
		visiteurRepository.deleteById(id);
	}

}
