package com.salas.gestaodesalas.domain.repository;

import com.salas.gestaodesalas.domain.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByDataAndSalaId(LocalDate data, Long salaId);
    List<Reserva> findBySalaId(Long salaId);
}
