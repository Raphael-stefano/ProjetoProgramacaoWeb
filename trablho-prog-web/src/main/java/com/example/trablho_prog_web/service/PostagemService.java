package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Postagem;
import com.example.trablho_prog_web.repository.PostagemRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {

    private final PostagemRepository repository;

    public PostagemService(PostagemRepository repository) {
        this.repository = repository;
    }

    public Optional<Postagem> findById(String id) {
        return repository.findById(id);
    }

    public List<Postagem> findAll() {
        return repository.findAll();
    }

    public Postagem save(Postagem postagem) {
        return repository.save(postagem);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public List<Postagem> findByAutorId(@Param("usuarioId") String usuarioId) {
        return repository.findByAutorId(usuarioId);
    }

    public List<Postagem> findByTitulo(@Param("titulo") String titulo) {
        return repository.findByTitulo(titulo);
    }

    public List<Postagem> findByAtivoTrue() {
        return repository.findByAtivoTrue();
    }

    public List<Postagem> findByAtivoFalse() {
        return repository.findByAtivoFalse();
    }

}

