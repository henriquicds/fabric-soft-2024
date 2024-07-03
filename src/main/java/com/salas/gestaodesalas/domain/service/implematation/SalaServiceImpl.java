package com.salas.gestaodesalas.domain.service.implematation;

import com.salas.gestaodesalas.domain.model.Sala;
import com.salas.gestaodesalas.domain.repository.SalaRepository;
import com.salas.gestaodesalas.domain.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaServiceImpl implements SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Override
    public List<Sala> listar() {
        return salaRepository.findAll();
    }

    @Override
    public void salvar(Sala sala) {
        salaRepository.save(sala);
    }

    @Override
    public Sala procurarPorId(Long id) {
        return salaRepository.findById(id).orElseThrow(() -> new RuntimeException("Sala não encontrada"));
    }

    @Override
    public void remover(Long id) {
        salaRepository.deleteById(id);
    }
}
