package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Usuario;
import com.example.trablho_prog_web.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Optional<Usuario> findById(String id) {
        Optional<Usuario> user = repository.findById(id);
        return user;
    }

    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Usuario> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    // READ
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Usuario atualizar(String id, Usuario usuarioAtualizado) {
        return repository.findById(id)
                .map(usuarioExistente -> {
                    usuarioAtualizado.setId(usuarioExistente.getId());
                    return repository.save(usuarioAtualizado);
                })
                .orElse(null);
    }

    @Transactional
    public boolean deletar(String id) {
        return repository.findById(id)
                .map(usuario -> {
                    repository.delete(usuario);
                    return true;
                })
                .orElse(false);
    }

}
