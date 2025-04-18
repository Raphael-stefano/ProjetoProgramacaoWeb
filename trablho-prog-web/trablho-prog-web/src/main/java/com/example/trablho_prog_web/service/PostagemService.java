package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Comentario;
import com.example.trablho_prog_web.model.Grupo;
import com.example.trablho_prog_web.model.Postagem;
import com.example.trablho_prog_web.repository.PostagemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostagemService {

    private final PostagemRepository repository;

    public PostagemService(PostagemRepository repository) {
        this.repository = repository;
    }

    // Operações CRUD básicas
    @Transactional
    public Postagem salvar(Postagem postagem) {
        if (postagem.getId() == null) {
            postagem.setId(UUID.randomUUID().toString());
        }
        return repository.save(postagem);
    }

    public Optional<Postagem> buscarPorId(String id) {
        return repository.findById(id);
    }

    public List<Postagem> listarTodas() {
        return repository.findAll();
    }

    @Transactional
    public void excluir(String id) {
        repository.deleteById(id);
    }

    @Transactional
    public Optional<Postagem> atualizar(String id, Postagem postagemAtualizada) {
        return repository.findById(id)
                .map(postagemExistente -> {
                    postagemAtualizada.setId(postagemExistente.getId());
                    return repository.save(postagemAtualizada);
                });
    }

    // Métodos específicos
    public List<Postagem> buscarPorAutor(String usuarioId) {
        return repository.findByAutorId(usuarioId);
    }

    public List<Postagem> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Postagem> buscarAtivas() {
        return repository.findByAtivoTrue();
    }

    public List<Postagem> buscarInativas() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public Optional<Postagem> ativarPostagem(String id) {
        return repository.findById(id)
                .map(postagem -> {
                    postagem.setAtivo(true);
                    return repository.save(postagem);
                });
    }

    @Transactional
    public Optional<Postagem> desativarPostagem(String id) {
        return repository.findById(id)
                .map(postagem -> {
                    postagem.setAtivo(false);
                    return repository.save(postagem);
                });
    }

    public Optional<Grupo> buscarGrupoDaPostagem(String postagemId) {
        return repository.findGrupoByPostagemId(postagemId);
    }

    public List<Postagem> buscarPorGrupo(String grupoId) {
        return repository.findByGrupoId(grupoId);
    }

    public List<Comentario> buscarComentariosPorPostagem(String postagemId) {
        return repository.findComentariosByPostagemId(postagemId);
    }

    public List<Comentario> buscarComentariosAtivosPorPostagem(String postagemId) {
        return repository.findComentariosAtivosByPostagemId(postagemId);
    }
}