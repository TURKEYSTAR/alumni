package com.misi2.springsecuritydemo.repository;

import com.misi2.springsecuritydemo.model.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlumniRepository extends JpaRepository<Alumni, Long> {
    Optional<Alumni> findByMatricule(String matricule);

    List<Alumni> findByPromotion(String promotion);

    @Query("SELECT a.promotion, COUNT(a) FROM Alumni a GROUP BY a.promotion ORDER BY a.promotion")
    List<Object[]> countAlumniByPromotion();

    @Query("SELECT a.anneeSoutenance, COUNT(a) FROM Alumni a GROUP BY a.anneeSoutenance ORDER BY a.anneeSoutenance")
    List<Object[]> countAlumniByAnnee();

    @Query("SELECT a.situationProfessionnelle, COUNT(a) FROM Alumni a GROUP BY a.situationProfessionnelle")
    List<Object[]> countAlumniBySituation();
}
