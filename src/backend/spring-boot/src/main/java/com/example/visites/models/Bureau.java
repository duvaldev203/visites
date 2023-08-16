package com.example.visites.models;

import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bureaux")
@AllArgsConstructor
@Getter
@Setter
public class Bureau {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "batiment")
	private String batiment;
	
	@Column(name = "etage")
	private String etage;
	
	@Column(name = "porte")
	private String porte;
    
    @OneToMany(mappedBy = "bureau", cascade = CascadeType.ALL)
    private List<User> users;
    	    
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private java.util.Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    private java.util.Date updatedAt;

	
	public Bureau() {
		// TODO Auto-generated constructor stub
	}

	public Bureau(Long id, String batiment, String etage, String porte) {
		super();
		this.id = id;
		this.batiment = batiment;
		this.etage = etage;
		this.porte = porte;
	}

}