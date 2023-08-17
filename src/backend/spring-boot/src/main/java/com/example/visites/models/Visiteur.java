package com.example.visites.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "visiteurs")
@Data
public class Visiteur extends Personne {

    @Column(name = "profession")
    private String profession;

    @OneToMany(mappedBy = "visiteur", cascade = CascadeType.ALL)
    private List<Visite> visites;

	public Visiteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Visiteur(Long id, String nom, String prenom, String sexe, String email, Date dateNais, String tel, String profession) {
		super(id, nom, prenom, sexe, email, dateNais, tel);
		this.profession = profession;
	}

}