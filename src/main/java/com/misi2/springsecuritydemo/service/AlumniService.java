package com.misi2.springsecuritydemo.service;

import com.misi2.springsecuritydemo.model.Alumni;
import com.misi2.springsecuritydemo.model.Role;
import com.misi2.springsecuritydemo.model.User;
import com.misi2.springsecuritydemo.repository.AlumniRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlumniService {

    private final AlumniRepository alumniRepository;
    private final UserService userService;

    public AlumniService(AlumniRepository alumniRepository, UserService userService) {
        this.alumniRepository = alumniRepository;
        this.userService = userService;
    }

    public List<Alumni> findAllAlumni() {
        return alumniRepository.findAll();
    }

    public Alumni findById(Long id) {
        return alumniRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumni not found with id: " + id));
    }

    public Alumni findByMatricule(String matricule) {
        return alumniRepository.findByMatricule(matricule)
                .orElseThrow(() -> new RuntimeException("Alumni not found with matricule: " + matricule));
    }

    public Alumni createAlumni(Alumni alumni) {
        // Sauvegarder l'alumni
        Alumni savedAlumni = alumniRepository.save(alumni);

        // Créer un compte utilisateur pour l'alumni
        // Login = matricule, Password = promotion
        User user = userService.createUserForAlumni(
                alumni.getMatricule(),
                alumni.getPromotion(),
                Role.ROLE_ALUMNI
        );

        user.setAlumni(savedAlumni);
        userService.updateUser(user);

        return savedAlumni;
    }

    public Alumni updateAlumni(Long id, Alumni alumniDetails) {
        Alumni alumni = findById(id);

        alumni.setMatricule(alumniDetails.getMatricule());
        alumni.setNom(alumniDetails.getNom());
        alumni.setPrenom(alumniDetails.getPrenom());
        alumni.setDiplomeSortie(alumniDetails.getDiplomeSortie());
        alumni.setAnneeSoutenance(alumniDetails.getAnneeSoutenance());
        alumni.setSujetMemoire(alumniDetails.getSujetMemoire());
        alumni.setSexe(alumniDetails.getSexe());
        alumni.setNationalite(alumniDetails.getNationalite());
        alumni.setSituationProfessionnelle(alumniDetails.getSituationProfessionnelle());
        alumni.setPromotion(alumniDetails.getPromotion());

        return alumniRepository.save(alumni);
    }

    public void deleteAlumni(Long id) {
        Alumni alumni = findById(id);

        // Supprimer l'utilisateur associé
        if (alumni.getUser() != null) {
            userService.deleteUser(alumni.getUser().getId());
        }

        alumniRepository.deleteById(id);
    }

    public List<Alumni> findByPromotion(String promotion) {
        return alumniRepository.findByPromotion(promotion);
    }

    // Méthodes pour les statistiques
    public long countTotal() {
        return alumniRepository.count();
    }

    public Map<String, Long> getAlumniByPromotion() {
        List<Object[]> results = alumniRepository.countAlumniByPromotion();
        Map<String, Long> stats = new HashMap<>();
        for (Object[] result : results) {
            stats.put((String) result[0], (Long) result[1]);
        }
        return stats;
    }

    public Map<Integer, Long> getAlumniByAnnee() {
        List<Object[]> results = alumniRepository.countAlumniByAnnee();
        Map<Integer, Long> stats = new HashMap<>();
        for (Object[] result : results) {
            stats.put((Integer) result[0], (Long) result[1]);
        }
        return stats;
    }

    public Map<String, Long> getAlumniBySituation() {
        List<Object[]> results = alumniRepository.countAlumniBySituation();
        Map<String, Long> stats = new HashMap<>();
        for (Object[] result : results) {
            stats.put((String) result[0], (Long) result[1]);
        }
        return stats;
    }
}
