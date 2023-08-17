package com.example.visites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.visites.models.Avis;

@Repository
public interface AvisRepository extends JpaRepository<Avis,Long>{

}
