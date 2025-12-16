package com.misi2.springsecuritydemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alumni")
public class Alumni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricule;

    private String nom;
    private String prenom;
    private String diplomeSortie;
    private Integer anneeSoutenance;
    private String sujetMemoire;
    private String sexe;
    private String nationalite;
    private String situationProfessionnelle;
    private String promotion;

    // Lien avec User pour l'authentification
    @OneToOne(mappedBy = "alumni", cascade = CascadeType.ALL)
    private User user;

    public String getNomComplet() {
        return prenom + " " + nom;
    }
}
