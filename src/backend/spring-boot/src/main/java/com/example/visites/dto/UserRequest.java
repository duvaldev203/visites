package com.example.visites.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String nom;
    private String prenom;
    private String sexe;
    private String email;
    private Date dateNais;
    private String tel;
    private String poste;
    private String username;
    private String password;
    private ProfileResponse profile;
    private BureauResponse bureau;
    private List<RoleResponse> roles;
}
