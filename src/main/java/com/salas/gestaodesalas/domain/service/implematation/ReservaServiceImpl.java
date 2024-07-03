package com.salas.gestaodesalas.domain.service.implematation;

import com.salas.gestaodesalas.domain.model.Reserva;
import com.salas.gestaodesalas.domain.repository.ReservaRepository;
import com.salas.gestaodesalas.domain.repository.SalaRepository;
import com.salas.gestaodesalas.domain.repository.UsuarioRepository;
import com.salas.gestaodesalas.domain.service.ReservaService;
import com.salas.gestaodesalas.domain.service.SalaService;
import com.salas.gestaodesalas.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public void salvar(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> getReservasPorSala(Long salaId) {
        return reservaRepository.findBySalaId(salaId);
    }

    @Override
    public Reserva procurar(Long id) {
        return reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    }

    @Override
    public void remover(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public boolean reservarSala(Long salaId, LocalDate data, LocalTime horaInicio,
                                LocalTime horaFim, String descricao, Long usuarioId) {
        List<Reserva> reservasExistentes = reservaRepository.findByDataAndSalaId(data, salaId);

        for (Reserva reserva : reservasExistentes) {
            if (horariosConflitantes(reserva.getHoraInicio(), reserva.getHoraFim(), horaInicio, horaFim)) {
                return false;
            }
        }

        Reserva novaReserva = new Reserva();
        novaReserva.setSala(salaRepository.findById(salaId).get());
        novaReserva.setData(data);
        novaReserva.setHoraInicio(horaInicio);
        novaReserva.setHoraFim(horaFim);
        novaReserva.setDescricao(descricao);
        novaReserva.setUsuario(usuarioRepository.findById(usuarioId).get());

        reservaRepository.save(novaReserva);
        return true;
    }

    private boolean horariosConflitantes(LocalTime inicioExistente, LocalTime fimExistente, LocalTime novoInicio, LocalTime novoFim) {
        return (novoInicio.isBefore(fimExistente) && novoFim.isAfter(inicioExistente));
    }
}
