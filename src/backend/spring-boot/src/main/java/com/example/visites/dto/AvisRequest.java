package com.example.visites.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvisRequest {
	
	private String libelle; 
	private VisiteResponse visite;
    private Timestamp createdAt;
    private Timestamp updatedAt;
	
}
