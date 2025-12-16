package com.misi2.springsecuritydemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // OAuth2 fields
    private String provider; // LOCAL, GOOGLE, etc.
    private String providerId; // Google user ID

    // Lien avec Alumni (pour les utilisateurs de type ALUMNI)
    @OneToOne
    @JoinColumn(name = "alumni_id")
    private Alumni alumni;
}