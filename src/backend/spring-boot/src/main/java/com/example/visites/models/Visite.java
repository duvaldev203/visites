package com.example.visites.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "visites")
@Getter
@Setter
public class Visite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motif")
    private String motif;

    @Column(name = "heure_debut")
    private LocalDateTime heureDebut;

    @Column(name = "heure_fin")
    private LocalDateTime heureFin;

    @Column(name = "date_visite")
    private LocalDate dateVisite;

    @Column(name = "type")
    private String type;
    
    @ManyToOne
    private User user;

    @ManyToOne
    private Visiteur visiteur;

    @OneToOne(mappedBy = "visite", cascade = CascadeType.ALL, orphanRemoval = true)
    private Avis avis;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private java.util.Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    private java.util.Date updatedAt;
    
    
	public Visite() {
		// TODO Auto-generated constructor stub
	}

	public Visite(Long id, String motif, LocalDateTime heureDebut, LocalDateTime heureFin, LocalDate dateVisite,
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