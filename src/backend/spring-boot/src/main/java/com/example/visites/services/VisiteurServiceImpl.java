package com.example.visites.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.visites.dto.VisiteurRequest;
import com.example.visites.dto.VisiteurResponse;
import com.example.visites.models.Visiteur;
import com.example.visites.repositories.VisiteurRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VisiteurServiceImpl implements VisiteurService {

	private final VisiteurRepository visiteurRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public VisiteurServiceImpl(VisiteurRepository visiteurRepository, ModelMapper modelMapper) {
		this.visiteurRepository = visiteurRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<List<VisiteurResponse>> index() {
		List<VisiteurResponse> visiteurs = visiteurRepository.findAll()
				.stream().map(el->modelMapper.map(el, VisiteurResponse.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(visiteurs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VisiteurResponse> show(Long Id) {
		Optional<Visiteur> visiteur = visiteurRepository.findById(Id);
		if (visiteur.isPresent())
			return new ResponseEntity<>(modelMapper.map(visiteur, VisiteurResponse.class), HttpStatus.FOUND);
		throw new EntityNotFoundException("Le visiteur n'a pas ete trouve !!!");
	}

	@Override
	public ResponseEntity<VisiteurResponse> create(VisiteurRequest visiteur) {
		Visiteur newVisiteur = modelMapper.map(visiteur, Visiteur.class);
		VisiteurResponse saved = modelMapper.map(visiteurRepository.save(newVisiteur), VisiteurResponse.class);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<VisiteurResponse> update(VisiteurRequest visiteur, Long id) {
		Optional<Visiteur> optVisiteur = visiteurRepository.findById(id);
		if (optVisiteur.isPresent()) {
			Visiteur oldVisiteur = modelMapper.map(optVisiteur, Visiteur.class);
			oldVisiteur.setId(id);
			VisiteurResponse updated = modelMapper.map(visiteurRepository.save(oldVisiteur), VisiteurResponse.class);
			return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
		}
		throw new EntityNotFoundException("Le visiteur a modifier n'a pas ete trouvee !!!");
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		visiteurRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
