package com.example.visites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.visites.models.Bureau;

@Repository
public interface BureauRepository extends JpaRepository<Bureau,Long>{

}
