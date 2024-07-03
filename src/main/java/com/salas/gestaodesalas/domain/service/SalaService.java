package com.salas.gestaodesalas.domain.service;

import com.salas.gestaodesalas.domain.model.Sala;

import java.util.List;

public interface SalaService {
    List<Sala> listar();
    Sala procurarPorId(Long id);
    void remover(Long id);
    void salvar(Sala sala);
}
