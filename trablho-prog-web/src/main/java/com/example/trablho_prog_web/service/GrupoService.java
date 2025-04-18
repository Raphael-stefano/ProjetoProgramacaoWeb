package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Grupo;
import com.example.trablho_prog_web.repository.GrupoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GrupoService {

    private final GrupoRepository repository;

    public GrupoService(GrupoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        if (grupo.getId() == null) {
            grupo.setId(UUID.randomUUID().toString());
        }
        return repository.save(grupo);
    }

    public Optional<Grupo> buscarPorId(String id) {
        return repository.findById(id);
    }

    public List<Grupo> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public void excluir(String id) {
        repository.deleteById(id);
    }

    public List<Grupo> buscarGruposPorUsuario(String usuarioId) {
        return repository.buscarGruposPorUsuario(usuarioId);
    }

    public List<Grupo> buscarGruposPorAdmin(String adminId) {
        return repository.buscarGruposPorAdmin(adminId);
    }

    @Transactional
    public Optional<Grupo> atualizar(String id, Grupo grupoAtualizado) {
        return repository.findById(id)
                .map(grupoExistente -> {
                    grupoAtualizado.setId(grupoExistente.getId());
                    return repository.save(grupoAtualizado);
                });
    }

    public boolean existePorId(String id) {
        return repository.existsById(id);
    }
}