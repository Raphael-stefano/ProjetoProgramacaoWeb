package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Postagem;
import com.example.trablho_prog_web.repository.PostagemRepository;
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
}

