package com.salas.gestaodesalas.domain.service;

import com.salas.gestaodesalas.domain.model.Reserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaService {
    void salvar(Reserva reserva);
    List<Reserva> listar();
    List<Reserva> getReservasPorSala(Long salaId);

    boolean reservarSala(Long salaId, LocalDate data, LocalTime horaInicio,
                         LocalTime horaFim, String descricao, Long usuarioId);
}
