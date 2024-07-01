package com.salas.gestaodesalas.domain.service;

import com.salas.gestaodesalas.domain.model.Sala;

import java.util.List;

public interface SalaService {
    List<Sala> listar();
    void salvar(Sala sala);
}
