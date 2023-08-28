package com.example.visites.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.visites.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.visites.dto.VisiteRequest;
import com.example.visites.dto.VisiteResponse;
import com.example.visites.models.Visite;
import com.example.visites.repositories.VisiteRepository;

@Service
public class VisiteServiceImpl implements VisiteService {
	
	private final VisiteRepository visiteRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public VisiteServiceImpl(VisiteRepository visiteRepository, ModelMapper modelMapper) {
		this.visiteRepository = visiteRepository;
		this.modelMapper = modelMapper;
	}
	@Override
	public ResponseEntity<List<VisiteResponse>> index() {
		List<VisiteResponse> visites = visiteRepository.findAll()
				.stream().map(el->modelMapper.map(el, VisiteResponse.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(visites, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VisiteResponse> show(Long id) {
		Visite visite = visiteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La Visite", "d'Id", id));
		return new ResponseEntity<>(modelMapper.map(visite, VisiteResponse.class), HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<VisiteResponse> create(VisiteRequest visite) {
		Visite newVisite = modelMapper.map(visite, Visite.class);
		VisiteResponse saved = modelMapper.map(visiteRepository.save(newVisite), VisiteResponse.class);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<VisiteResponse> update(VisiteRequest visite, Long id) {
		visiteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La Visite que vous voulez modifier ", "d'Id", id));
		Visite oldVisite = modelMapper.map(visite, Visite.class);
		oldVisite.setId(id);
		VisiteResponse updated = modelMapper.map(visiteRepository.save(oldVisite), VisiteResponse.class);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		Visite visite = visiteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La Visite que vous voulez supprimer ", "d'Id", id));
		visiteRepository.delete(visite);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<VisiteResponse>> records(String search) {
		List<Visite> visites = visiteRepository.findByMotifContaining(search);
		List<VisiteResponse> resp = visites.stream().map(el->modelMapper.map(el, VisiteResponse.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(resp, HttpStatus.MULTIPLE_CHOICES);
	}

	@Override
	public ResponseEntity<List<VisiteResponse>> sort(String motif) {
		List<Visite> visites = visiteRepository.findByMotifContaining(motif);
		visites.removeIf(v -> v.getAvis() != null);
		List<VisiteResponse> resp = visites.stream().map(el->modelMapper.map(el, VisiteResponse.class))
				.toList();
		return  new ResponseEntity<>(resp, HttpStatus.MULTIPLE_CHOICES);
	}

	@Override
	public ResponseEntity<List<VisiteResponse>> getVisiteByEmployeId(Long employeId) {
		List<Visite> visites = visiteRepository.findByUserId(employeId);
		return null;
	}

}
