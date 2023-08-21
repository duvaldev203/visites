package com.example.visites.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.visites.dto.VisiteRequest;
import com.example.visites.dto.VisiteResponse;
import com.example.visites.models.Visite;
import com.example.visites.repositories.VisiteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VisiteServiceImpl implements VisiteService {
	
	private final VisiteRepository visiteRepository;
	private ModelMapper modelMapper;

	@Autowired
	public VisiteServiceImpl(VisiteRepository visiteRepository, ModelMapper modelMapper) {
		this.visiteRepository = visiteRepository;
		this.modelMapper = modelMapper;
	}
	@Override
	public List<VisiteResponse> index() {
		return visiteRepository.findAll()
				.stream().map(el->modelMapper.map(el, VisiteResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public VisiteResponse show(Long Id) {
		Optional<Visite> visite = visiteRepository.findById(Id);
		if (visite.isPresent())
			return modelMapper.map(visite, VisiteResponse.class);
		throw new EntityNotFoundException("La visite n'a pas ete trouve !!!");
	}

	@Override
	public VisiteResponse create(VisiteRequest visite) {
		Visite newVisite = modelMapper.map(visite, Visite.class);
		Visite saved = visiteRepository.save(newVisite);
		return modelMapper.map(saved, VisiteResponse.class);
	}

	@Override
	public VisiteResponse update(VisiteRequest visite, Long id) {
		Optional<Visite> optVisite = visiteRepository.findById(id);
		if (optVisite.isPresent()) {
			Visite oldVisite = modelMapper.map(visite, Visite.class);
			oldVisite.setId(id);
			Visite updated = visiteRepository.save(oldVisite);
			return modelMapper.map(updated, VisiteResponse.class);
		}
		throw new EntityNotFoundException("La visite a modifier n'a pas ete trouvee !!!");
	}

	@Override
	public void delete(Long id) {
		visiteRepository.deleteById(id);
	}
	
}
