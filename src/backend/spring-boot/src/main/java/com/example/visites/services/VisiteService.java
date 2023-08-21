package com.example.visites.services;

import java.util.List;

import com.example.visites.dto.VisiteRequest;
import com.example.visites.dto.VisiteResponse;

public interface VisiteService {
	
	List<VisiteResponse> index();
	
	VisiteResponse show(Long Id);
	
	VisiteResponse create(VisiteRequest visite);

	VisiteResponse update(VisiteRequest visite, Long id);
	
	void delete(Long id);

}
