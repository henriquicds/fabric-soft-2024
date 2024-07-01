package com.salas.gestaodesalas.controller;

import com.salas.gestaodesalas.domain.model.Sala;
import com.salas.gestaodesalas.domain.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/salas")
public class SalaController {
    @Autowired
    private SalaService salaService;
    @GetMapping
    public String listarSalas(Model model) {
        model.addAttribute("salas", salaService.listar());
        return "index";
    }

    @GetMapping("/nova")
    public String mostrarFormularioSala(Model model) {
        model.addAttribute("sala", new Sala());
        return "formulario_sala";
    }

    @PostMapping("/nova")
    public String salvarSala(@ModelAttribute("sala") Sala sala) {
        salaService.salvar(sala);
        return "redirect:/salas";
    }
}
