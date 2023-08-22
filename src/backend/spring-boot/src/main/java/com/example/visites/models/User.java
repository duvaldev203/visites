package com.example.visites.models;

import java.io.Serial;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Personne {

    @Serial
    private static final long serialVersionUID = 1L;

	@Column(name = "poste")
    @NotNull(message = "poste : Ce champ est obligatoire")
    @Size(min = 5, max = 50, message = "poste : Le taille de ce champ doit etre comprise entre 5 et 50")
    private String poste;

    @Column(name = "username")
    @NotBlank(message = "username : Ce champ ne doit pas etre vide")
    @NotNull(message = "username : Ce champ est obligatoire")
    private String username;

    @NotNull(message = "password : Ce champ est obligatoire")
    @Size(min = 8, max = 15, message = "password : La taille de ce champ doit etre compris entre 8 et 15")
    @Column(name = "password")
    private String password;
    
    @ManyToOne
    private Bureau bureau;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Visite> visites;

    @ManyToMany
    @JoinTable(name = "user_role",
    	joinColumns = @JoinColumn(name = "user_id"),
    	inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
    

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String nom, String prenom, String sexe, String email, Date dateNais, String tel, String poste, String username, String password) {
		super(id, nom, prenom, sexe, email, dateNais, tel);
		this.poste = poste;
		this.username = username;
		this.password = password;
	}
	
}