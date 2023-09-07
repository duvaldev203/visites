package com.example.visites.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.visites.exceptions.APIException;
import com.example.visites.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.visites.dto.AvisRequest;
import com.example.visites.dto.AvisResponse;
import com.example.visites.models.Avis;
import com.example.visites.repositories.AvisRepository;

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
	public ResponseEntity<AvisResponse> show(Long id) {
		Avis avis = avisRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("L'Avis", "d'Id", id));
		return new ResponseEntity<>(modelMapper.map(avis, AvisResponse.class), HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<AvisResponse> create(AvisRequest avis) {
		Avis avisEntite = modelMapper.map(avis, Avis.class);
		Optional<Avis> opt = avisRepository.findByVisiteId(avisEntite.getVisite().getId());
		if (opt.isPresent())
			throw new APIException("La visite que vous avez selectione est associe a un avis !!!");
		AvisResponse saved = modelMapper.map(avisRepository.save(avisEntite), AvisResponse.class);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<AvisResponse> update(AvisRequest avis, Long id) {
		avisRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("L'Avis que vous voulez modifier ", "d'Id", id));
		Avis oldAvis = modelMapper.map(avis, Avis.class);
		Optional<Avis> opt = avisRepository.findByVisiteId(avis.getVisite().getId());
		if (opt.isPresent())
			throw new APIException("La visite que vous avez selectione est associe a un avis !!!");
		oldAvis.setId(id);
		AvisResponse updated = modelMapper.map(avisRepository.save(oldAvis), AvisResponse.class);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		Avis avis = avisRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("L'Avis que voulez supprimer ", "d'Id", id));
		avisRepository.delete(avis);
		return ResponseEntity.noContent().build();
	}

}
