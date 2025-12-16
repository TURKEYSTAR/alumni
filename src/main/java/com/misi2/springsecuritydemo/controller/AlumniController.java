package com.misi2.springsecuritydemo.controller;

import com.misi2.springsecuritydemo.config.CustomUserDetails;
import com.misi2.springsecuritydemo.model.Alumni;
import com.misi2.springsecuritydemo.service.AlumniService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alumni")
@PreAuthorize("hasRole('ALUMNI')")
public class AlumniController {

    private final AlumniService alumniService;

    public AlumniController(AlumniService alumniService) {
        this.alumniService = alumniService;
    }

    @GetMapping("/profile")
    public String viewProfile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Alumni alumni = userDetails.getUser().getAlumni();
        if (alumni == null) {
            return "redirect:/login?error=no_alumni_profile";
        }
        model.addAttribute("alumni", alumni);
        return "alumni/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                                @ModelAttribute Alumni alumniUpdate) {
        Alumni alumni = userDetails.getUser().getAlumni();
        if (alumni == null) {
            return "redirect:/login?error=no_alumni_profile";
        }

        // Mise à jour des informations modifiables par l'alumni
        alumniService.updateAlumni(alumni.getId(), alumniUpdate);
        return "redirect:/alumni/profile?success=updated";
    }
}
