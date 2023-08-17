package com.example.visites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.visites.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
