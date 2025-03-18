package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Grupo;
import com.example.trablho_prog_web.repository.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    private final GrupoRepository repository;

    public GrupoService(GrupoRepository repository) {
        this.repository = repository;
    }

    public Grupo salvar(Grupo grupo) {
        return repository.save(grupo);
    }

    public Optional<Grupo> buscarPorId(String id) {
        return repository.findById(id);
    }

    public List<Grupo> listarTodos() {
        return repository.findAll();
    }

    public void excluir(String id) {
        repository.deleteById(id);
    }

    public List<Grupo> buscarGruposPorUsuario(String id) {
        return repository.buscarGruposPorUsuario(id);
    }

    public List<Grupo> buscarGruposPorAdmin(String id) {
        return repository.buscarGruposPorAdmin(id);
    }

}
