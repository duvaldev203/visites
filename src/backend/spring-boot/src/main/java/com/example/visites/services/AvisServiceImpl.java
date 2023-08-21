package com.example.visites.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	private ModelMapper modelMapper;

	@Override
	public List<AvisResponse> index() {
		return avisRepository.findAll()
				.stream().map(el->modelMapper.map(el, AvisResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public AvisResponse show(Long Id) {
		Optional<Avis> avis = avisRepository.findById(Id);
		if (avis.isPresent())
			return modelMapper.map(avis.get(), AvisResponse.class);
		throw new EntityNotFoundException("L'avis n'a pas ete trouve");
	}

	@Override
	public AvisResponse create(AvisRequest avis) {
		Avis avisEntite = modelMapper.map(avis, Avis.class);
		Optional<Avis> opt = avisRepository.findByVisiteId(avisEntite.getVisite().getId());
		if (opt.isPresent())
			throw new EntityExistsException("La visite que vous avez selectione est associe a un avis !!!");
		Avis saved = avisRepository.save(avisEntite);
		return modelMapper.map(saved, AvisResponse.class);
	}

	@Override
	public AvisResponse update(AvisRequest avis, Long id) {
		Optional<Avis> optAvis = avisRepository.findById(id);
		if (optAvis.isPresent()) {
			Avis oldAvis = modelMapper.map(avis, Avis.class);	
			oldAvis.setId(id);
			Avis updated = avisRepository.save(oldAvis);
			return modelMapper.map(updated, AvisResponse.class);
		}
		throw new EntityNotFoundException("L'avis a modifier n'a pas ete trouve !!!");
	}

	@Override
	public void delete(Long id) {
		avisRepository.deleteById(id);
	}

}
