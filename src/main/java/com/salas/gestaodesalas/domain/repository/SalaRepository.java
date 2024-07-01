package com.salas.gestaodesalas.domain.repository;

import com.salas.gestaodesalas.domain.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long>{
}
