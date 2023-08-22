package com.example.visites.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.visites.dto.AvisRequest;
import com.example.visites.dto.AvisResponse;
import com.example.visites.models.Avis;
import com.example.visites.repositories.AvisRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AvisServiceImpl implements AvisService {
	
	private final AvisRepository avisRepository;
	private final ModelMapper modelMapper;

	@Override
	public ResponseEntity<List<AvisResponse>> index() {
		List<AvisResponse> avis = avisRepository.findAll()
				.stream().map(el->modelMapper.map(el, AvisResponse.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(avis, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AvisResponse> show(Long Id) {
		Optional<Avis> avis = avisRepository.findById(Id);
		if (avis.isPresent())
			return new ResponseEntity<>(modelMapper.map(avis.get(), AvisResponse.class), HttpStatus.FOUND);
		throw new EntityNotFoundException("L'avis n'a pas ete trouve");
	}

	@Override
	public ResponseEntity<AvisResponse> create(AvisRequest avis) {
		Avis avisEntite = modelMapper.map(avis, Avis.class);
		Optional<Avis> opt = avisRepository.findByVisiteId(avisEntite.getVisite().getId());
		if (opt.isPresent())
			throw new EntityExistsException("La visite que vous avez selectione est associe a un avis !!!");
		AvisResponse saved = modelMapper.map(avisRepository.save(avisEntite), AvisResponse.class);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<AvisResponse> update(AvisRequest avis, Long id) {
		Optional<Avis> optAvis = avisRepository.findById(id);
		if (optAvis.isPresent()) {
			Avis oldAvis = modelMapper.map(avis, Avis.class);	
			oldAvis.setId(id);
			AvisResponse updated = modelMapper.map(avisRepository.save(oldAvis), AvisResponse.class);
			return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
		}
		throw new EntityNotFoundException("L'avis a modifier n'a pas ete trouve !!!");
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		avisRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
