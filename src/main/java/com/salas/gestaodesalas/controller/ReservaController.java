package com.salas.gestaodesalas.controller;

import com.salas.gestaodesalas.domain.model.Reserva;
import com.salas.gestaodesalas.domain.service.ReservaService;
import com.salas.gestaodesalas.domain.service.SalaService;
import com.salas.gestaodesalas.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private SalaService salaService;
    @Autowired
    private UsuarioService userService;

    @GetMapping
    public String listarReservas(Model model) {
        model.addAttribute("reservas", reservaService.listar());
        return "reservas";
    }

    @GetMapping("/agendar")
    public String mostrarPaginaAgendamento(@RequestParam Long salaId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long usuarioId = null;

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            usuarioId = userService.buscarPorEmail(userDetails.getUsername()).getId();
        }

        if (usuarioId == null) {
            return "redirect:/login";
        }

        model.addAttribute("salaId", salaId);
        model.addAttribute("usuarioId", usuarioId);

        List<Reserva> reservas = reservaService.getReservasPorSala(salaId);
        model.addAttribute("reservas", reservas);

        return "agendarReuniao";
    }

    @GetMapping("/new")
    public String novaReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("salas", salaService.listar());
        return "reserva-form";
    }

    @PostMapping
    public String salvarReserva(@ModelAttribute Reserva reserva, RedirectAttributes redirectAttributes) {
        reservaService.salvar(reserva);
        redirectAttributes.addAttribute("salaId", reserva.getSala().getId());
        return "redirect:/";
    }

    @PostMapping("/agendar")
    public String agendarReuniao(@RequestParam("salaId") Long salaId,
                                 @RequestParam("data") String data,
                                 @RequestParam("horaInicio") String horaInicio,
                                 @RequestParam("horaFim") String horaFim,
                                 @RequestParam("descricao") String descricao,
                                 @RequestParam("usuarioId") Long usuarioId,
                                 Model model) {
        LocalDate dataReuniao = LocalDate.parse(data);
        LocalTime inicio = LocalTime.parse(horaInicio);
        LocalTime fim = LocalTime.parse(horaFim);

        boolean reservado = reservaService.reservarSala(salaId, dataReuniao, inicio, fim, descricao, usuarioId);
        if (reservado) {
            model.addAttribute("mensagem", "Reunião agendada com sucesso!");
        } else {
            model.addAttribute("mensagem", "Não foi possível agendar a reunião. Verifique os horários e tente novamente.");
        }

        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarReserva(@PathVariable Long id, Model model) {
        Reserva reserva = reservaService.procurar(id);
        model.addAttribute("reserva", reserva);
        model.addAttribute("salas", salaService.listar());
        return "editarReserva";
    }

    @PostMapping("/editar")
    public String salvarEdicao(@ModelAttribute Reserva reserva, RedirectAttributes redirectAttributes) {
        reservaService.salvar(reserva);
        return "redirect:/";
    }

    @PostMapping("/excluir/{id}")
    public String excluirReserva(@PathVariable Long id) {
        reservaService.remover(id);
        return "redirect:/";
    }
}
