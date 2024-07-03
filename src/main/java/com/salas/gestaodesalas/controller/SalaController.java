package com.salas.gestaodesalas.controller;

import com.salas.gestaodesalas.domain.model.Sala;
import com.salas.gestaodesalas.domain.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Sala sala = salaService.procurarPorId(id);
        model.addAttribute("sala", sala);
        return "formulario_sala";
    }

    @PostMapping("/editar")
    public String editarSala(@ModelAttribute("sala") Sala sala) {
        salaService.salvar(sala);
        return "redirect:/salas";
    }

    @PostMapping("/excluir/{id}")
    public String excluirSala(@PathVariable Long id) {
        salaService.remover(id);
        return "redirect:/";
    }
}
