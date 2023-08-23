package com.example.visites.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.visites.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.visites.dto.VisiteurRequest;
import com.example.visites.dto.VisiteurResponse;
import com.example.visites.models.Visiteur;
import com.example.visites.repositories.VisiteurRepository;

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
	public ResponseEntity<VisiteurResponse> show(Long id) {
		Visiteur visiteur = visiteurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Le Visiteur", "d'Id", id));
		return new ResponseEntity<>(modelMapper.map(visiteur, VisiteurResponse.class), HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<VisiteurResponse> create(VisiteurRequest visiteur) {
		Visiteur newVisiteur = modelMapper.map(visiteur, Visiteur.class);
		VisiteurResponse saved = modelMapper.map(visiteurRepository.save(newVisiteur), VisiteurResponse.class);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<VisiteurResponse> update(VisiteurRequest visiteur, Long id) {
		visiteurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Le Visiteur que vous voulez modifier ", "d'Id", id));
		Visiteur oldVisiteur = modelMapper.map(visiteur, Visiteur.class);
		oldVisiteur.setId(id);
		VisiteurResponse updated = modelMapper.map(visiteurRepository.save(oldVisiteur), VisiteurResponse.class);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		Visiteur visiteur = visiteurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Le Visiteur", "d'Id", id));
		visiteurRepository.delete(visiteur);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<VisiteurResponse>> records(String search) {
		List<Visiteur> visiteurs = visiteurRepository.findByNomContainingOrPrenomContainingOrEmailContainingOrTelContaining(search, search, search, search);
		List<VisiteurResponse> resp = visiteurs.stream().map(el->modelMapper.map(el, VisiteurResponse.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(resp, HttpStatus.MULTIPLE_CHOICES);
	}

}
