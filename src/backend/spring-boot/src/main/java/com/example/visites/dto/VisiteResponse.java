package com.example.visites.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisiteResponse {
	private Long id;
    private String motif;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private LocalDate dateVisite;
    private String type;
    private UserResponse user;
    private VisiteurResponse visiteur;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
