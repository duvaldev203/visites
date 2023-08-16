package com.example.visites.models;

import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
public abstract class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "email")
    private String email;

    @Column(name = "date_nais")
    private Date dateNais;

    @Column(name = "tel")
    private String tel;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private java.util.Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    private java.util.Date updatedAt;
    

	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personne(Long id, String nom, String prenom, String sexe, String email, Date dateNais, String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.email = email;
		this.dateNais = dateNais;
		this.tel = tel;
	}

}