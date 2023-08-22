package com.example.visites.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.visites.dto.VisiteRequest;
import com.example.visites.dto.VisiteResponse;
import com.example.visites.models.Visite;
import com.example.visites.repositories.VisiteRepository;

import jakarta.persistence.EntityNotFoundException;

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
	public ResponseEntity<VisiteResponse> show(Long Id) {
		Optional<Visite> visite = visiteRepository.findById(Id);
		if (visite.isPresent())
			return new ResponseEntity<>(modelMapper.map(visite, VisiteResponse.class), HttpStatus.FOUND);
		throw new EntityNotFoundException("La visite n'a pas ete trouve !!!");
	}

	@Override
	public ResponseEntity<VisiteResponse> create(VisiteRequest visite) {
		Visite newVisite = modelMapper.map(visite, Visite.class);
		VisiteResponse saved = modelMapper.map(visiteRepository.save(newVisite), VisiteResponse.class);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<VisiteResponse> update(VisiteRequest visite, Long id) {
		Optional<Visite> optVisite = visiteRepository.findById(id);
		if (optVisite.isPresent()) {
			Visite oldVisite = modelMapper.map(visite, Visite.class);
			oldVisite.setId(id);
			VisiteResponse updated = modelMapper.map(visiteRepository.save(oldVisite), VisiteResponse.class);
			return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
		}
		throw new EntityNotFoundException("La visite a modifier n'a pas ete trouvee !!!");
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		visiteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
