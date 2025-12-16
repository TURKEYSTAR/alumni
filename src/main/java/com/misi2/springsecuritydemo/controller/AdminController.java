package com.misi2.springsecuritydemo.controller;

import com.misi2.springsecuritydemo.model.Alumni;
import com.misi2.springsecuritydemo.service.AlumniService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AlumniService alumniService;

    public AdminController(AlumniService alumniService) {
        this.alumniService = alumniService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalAlumni", alumniService.countTotal());
        model.addAttribute("alumniByPromotion", alumniService.getAlumniByPromotion());
        model.addAttribute("alumniByAnnee", alumniService.getAlumniByAnnee());
        model.addAttribute("alumniBySituation", alumniService.getAlumniBySituation());
        model.addAttribute("alumniList", alumniService.findAllAlumni());
        return "admin/dashboard";
    }

    @PostMapping("/dashboard")
    public String createAlumni(@ModelAttribute Alumni alumni) {
        alumniService.createAlumni(alumni);
        return "redirect:/admin/dashboard?success=created";
    }

    @PostMapping("/dashboard/{id}/edit")
    public String updateAlumni(@PathVariable Long id, @ModelAttribute Alumni alumni) {
        alumniService.updateAlumni(id, alumni);
        return "redirect:/admin/dashboard?success=updated";
    }

    @PostMapping("/dashboard/{id}/delete")
    public String deleteAlumni(@PathVariable Long id) {
        alumniService.deleteAlumni(id);
        return "redirect:/admin/dashboard?success=deleted";
    }

    @GetMapping("/alumni-detail/{id}")
    public String viewAlumni(@PathVariable Long id, Model model) {
        model.addAttribute("alumni", alumniService.findById(id));
        return "admin/alumni-detail";
    }
}