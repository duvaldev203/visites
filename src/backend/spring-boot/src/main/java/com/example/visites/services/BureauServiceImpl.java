package com.example.visites.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.visites.models.Bureau;
import com.example.visites.repositories.BureauRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BureauServiceImpl implements BureauService {
    
	public final BureauRepository bureauRepository;

	@Autowired
	public BureauServiceImpl(BureauRepository bureauRepository) {
		this.bureauRepository = bureauRepository;
	}
    
	@Override
    public ResponseEntity<List<Bureau>> index() {
    	return new ResponseEntity<>(bureauRepository.findAll(), HttpStatus.OK);
    }

	@Override
    public ResponseEntity<Bureau> show(Long id) {
    	Optional<Bureau> bureau = bureauRepository.findById(id);
    	if (bureau.isPresent())
    		return new ResponseEntity<>(bureau.get(), HttpStatus.FOUND);
    	throw new EntityNotFoundException("Le bureau n'a pas ete trouve !!!");
    }
	
	@Override
    public ResponseEntity<Bureau> create(Bureau bureau) {
    	Bureau newBureau = bureauRepository.save(bureau);
    	return new ResponseEntity<>(newBureau, HttpStatus.CREATED);
    }

	@Override
    public ResponseEntity<Bureau> update(Bureau bureau, Long id) {
    	Optional<Bureau> optBureau = bureauRepository.findById(id);
    	if (optBureau.isPresent()) {
    		Bureau oldBureau = optBureau.get();
    		oldBureau.setBatiment(bureau.getBatiment());
    		oldBureau.setEtage(bureau.getEtage());
    		oldBureau.setPorte(bureau.getPorte());
    		return new ResponseEntity<>(bureauRepository.save(oldBureau), HttpStatus.ACCEPTED);
    	}    	
    	throw new EntityNotFoundException("Le bureau a modifier n'a pas ete trouve !!!");
    }

	@Override
    public ResponseEntity<?> delete(Long id) {
    	bureauRepository.deleteById(id);
    	return ResponseEntity.noContent().build();
    }
    
}
