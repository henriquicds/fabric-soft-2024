package com.salas.gestaodesalas.controller;

import com.salas.gestaodesalas.domain.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private SalaService salaService;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("salas", salaService.listar());
        model.addAttribute("username", userDetails != null ? userDetails.getUsername() : null);
        return "index";
    }
}
