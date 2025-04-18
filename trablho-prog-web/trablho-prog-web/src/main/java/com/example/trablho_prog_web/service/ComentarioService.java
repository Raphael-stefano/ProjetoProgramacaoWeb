package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Comentario;
import com.example.trablho_prog_web.repository.ComentarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComentarioService {

    private final ComentarioRepository repository;

    public ComentarioService(ComentarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Comentario criar(Comentario comentario) {
        if (comentario.getId() == null) {
            comentario.setId(UUID.randomUUID().toString());
        }
        return repository.save(comentario);
    }

    public List<Comentario> listarTodos() {
        return repository.findAll();
    }

    public Optional<Comentario> findById(String id) {
        return repository.findById(id);
    }

    @Transactional
    public Optional<Comentario> atualizar(String id, Comentario comentarioAtualizado) {
        return repository.findById(id)
                .map(comentarioExistente -> {
                    comentarioAtualizado.setId(comentarioExistente.getId());
                    return repository.save(comentarioAtualizado);
                });
    }

    @Transactional
    public boolean deletar(String id) {
        return repository.findById(id)
                .map(comentario -> {
                    repository.delete(comentario);
                    return true;
                })
                .orElse(false);
    }

    public List<Comentario> findByUsuario(String usuarioId) {
        return repository.findByAutor_id(usuarioId);
    }
}