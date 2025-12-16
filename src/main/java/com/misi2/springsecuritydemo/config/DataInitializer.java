package com.misi2.springsecuritydemo.config;

import com.misi2.springsecuritydemo.model.Alumni;
import com.misi2.springsecuritydemo.model.Client;
import com.misi2.springsecuritydemo.model.Role;
import com.misi2.springsecuritydemo.service.AlumniService;
import com.misi2.springsecuritydemo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserService userService, AlumniService alumniService) {
        return args -> {
            // Créer un compte Admin
            if (userService.findAllUsers().isEmpty()) {
                userService.createUser("admin", "admin123", Role.ROLE_ADMIN);
                // Créer quelques alumni de test
                Client c1 = new Client();
                // Créer quelques alumni de test
                Alumni alumni1 = new Alumni();
                alumni1.setMatricule("ALU2020001");
                alumni1.setNom("DIOP");
                alumni1.setPrenom("Amadou");
                alumni1.setDiplomeSortie("Master en Informatique");
                alumni1.setAnneeSoutenance(2020);
                alumni1.setSujetMemoire("Intelligence Artificielle et Big Data");
                alumni1.setSexe("M");
                alumni1.setNationalite("Sénégalaise");
                alumni1.setSituationProfessionnelle("En poste");
                alumni1.setPromotion("2020");
                alumniService.createAlumni(alumni1);

                Alumni alumni2 = new Alumni();
                alumni2.setMatricule("ALU2021002");
                alumni2.setNom("NDIAYE");
                alumni2.setPrenom("Fatou");
                alumni2.setDiplomeSortie("Master en Gestion");
                alumni2.setAnneeSoutenance(2021);
                alumni2.setSujetMemoire("Management de la qualité dans les PME");
                alumni2.setSexe("F");
                alumni2.setNationalite("Sénégalaise");
                alumni2.setSituationProfessionnelle("En poste");
                alumni2.setPromotion("2021");
                alumniService.createAlumni(alumni2);

                Alumni alumni3 = new Alumni();
                alumni3.setMatricule("ALU2022003");
                alumni3.setNom("FALL");
                alumni3.setPrenom("Moussa");
                alumni3.setDiplomeSortie("Master en Réseaux");
                alumni3.setAnneeSoutenance(2022);
                alumni3.setSujetMemoire("Sécurité des réseaux 5G");
                alumni3.setSexe("M");
                alumni3.setNationalite("Sénégalaise");
                alumni3.setSituationProfessionnelle("Entrepreneur");
                alumni3.setPromotion("2022");
                alumniService.createAlumni(alumni3);

                Alumni alumni4 = new Alumni();
                alumni4.setMatricule("ALU2023004");
                alumni4.setNom("SOW");
                alumni4.setPrenom("Aïssatou");
                alumni4.setDiplomeSortie("Master en Marketing");
                alumni4.setAnneeSoutenance(2023);
                alumni4.setSujetMemoire("Marketing digital et réseaux sociaux");
                alumni4.setSexe("F");
                alumni4.setNationalite("Sénégalaise");
                alumni4.setSituationProfessionnelle("En recherche");
                alumni4.setPromotion("2023");
                alumniService.createAlumni(alumni4);
            }
        };
    }
}