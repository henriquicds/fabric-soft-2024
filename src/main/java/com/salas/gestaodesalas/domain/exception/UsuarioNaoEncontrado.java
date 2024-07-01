package com.salas.gestaodesalas.domain.exception;

public class UsuarioNaoEncontrado extends Exception{
    public UsuarioNaoEncontrado(String email) {
        super("Usuário não encontrado para o email: " + email);
    }
    public UsuarioNaoEncontrado(Long id) {
        super("Usuário não encontrado para o id: " + id);
    }
    public UsuarioNaoEncontrado() {
        super("Usuário não encontrado");
    }
}
