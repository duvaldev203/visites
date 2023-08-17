package com.example.visites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.visites.models.Visiteur;

@Repository
public interface VisiteurRepository extends JpaRepository<Visiteur, Long> {

}
