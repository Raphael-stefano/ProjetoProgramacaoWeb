package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Comentario;
import com.example.trablho_prog_web.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComentarioService {

    private final ComentarioRepository repository;

    public ComentarioService(ComentarioRepository repository) {
        this.repository = repository;
    }

    public Optional<Comentario> findById(String id) {
        return repository.findById(id);
    }
}
