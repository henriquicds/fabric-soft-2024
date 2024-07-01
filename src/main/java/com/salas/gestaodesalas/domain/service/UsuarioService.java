package com.salas.gestaodesalas.domain.service;

import com.salas.gestaodesalas.domain.model.Usuario;

public interface UsuarioService {
    void salvar(Usuario usuario);
    Usuario buscarPorEmail(String email);
}
