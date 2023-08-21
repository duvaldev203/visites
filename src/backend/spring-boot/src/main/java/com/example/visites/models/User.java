package com.example.visites.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Personne {

    private static final long serialVersionUID = 1L;

	@Column(name = "poste")
    private String poste;

    @Column(name = "username")
    private String username;

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