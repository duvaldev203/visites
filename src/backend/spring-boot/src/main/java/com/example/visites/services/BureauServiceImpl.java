package com.example.visites.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.visites.dto.BureauRequest;
import com.example.visites.dto.BureauResponse;
import com.example.visites.models.Bureau;
import com.example.visites.repositories.BureauRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BureauServiceImpl implements BureauService {
    
	private final BureauRepository bureauRepository;
	private ModelMapper modelMapper;

	@Autowired
	public BureauServiceImpl(BureauRepository bureauRepository, ModelMapper modelMapper) {
		this.bureauRepository = bureauRepository;
		this.modelMapper = modelMapper;
	}
    
	@Override
    public ResponseEntity<List<BureauResponse>> index() {
		List<BureauResponse> bureaux = bureauRepository.findAll()
		.stream().map(el->modelMapper.map(el, BureauResponse.class))
		.collect(Collectors.toList());
    	return new ResponseEntity<>(bureaux, HttpStatus.OK);
    }

	@Override
    public ResponseEntity<BureauResponse> show(Long id) {
    	Optional<Bureau> bureau = bureauRepository.findById(id);
    	if (bureau.isPresent())
    		return new ResponseEntity<>(modelMapper.map(bureau.get(), BureauResponse.class), HttpStatus.FOUND);
    	throw new EntityNotFoundException("Le bureau n'a pas ete trouve !!!");
    }
	
	@Override
    public ResponseEntity<BureauResponse> create(BureauRequest bureau) {
    	Bureau newBureau = modelMapper.map(bureau, Bureau.class);
    	Bureau saved = bureauRepository.save(newBureau);
    	return new ResponseEntity<>(modelMapper.map(saved, BureauResponse.class), HttpStatus.CREATED);
    }

	@Override
    public ResponseEntity<BureauResponse> update(BureauRequest bureau, Long id) {
    	Optional<Bureau> optBureau = bureauRepository.findById(id);
    	if (optBureau.isPresent()) {
    		Bureau oldBureau = modelMapper.map(bureau, Bureau.class);
    		oldBureau.setId(id);
    		BureauResponse updated = modelMapper.map(oldBureau, BureauResponse.class);
    		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    	}    	
    	throw new EntityNotFoundException("Le bureau a modifier n'a pas ete trouve !!!");
    }

	@Override
    public ResponseEntity<?> delete(Long id) {
    	bureauRepository.deleteById(id);
    	return ResponseEntity.noContent().build();
    }
    
}
