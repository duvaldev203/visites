package com.example.visites.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "visites")
@Data
public class Visite implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motif")
    private String motif;

    @Column(name = "heure_debut")
    private LocalTime heureDebut;

    @Column(name = "heure_fin")
    private LocalTime heureFin;

    @Column(name = "date_visite")
    private LocalDate dateVisite;

    @Column(name = "type")
    private String type;
    
    @OneToOne(mappedBy = "visite", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private Avis avis;
    
    @ManyToOne
    private User user;

    @ManyToOne
    private Visiteur visiteur;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
    
    
	public Visite() {
		// TODO Auto-generated constructor stub
	}

	public Visite(Long id, String motif, LocalTime heureDebut, LocalTime heureFin, LocalDate dateVisite,
			String type) {
		super();
		this.id = id;
		this.motif = motif;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.dateVisite = dateVisite;
		this.type = type;
	}
	
}