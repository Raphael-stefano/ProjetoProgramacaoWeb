package com.example.trablho_prog_web.model;

import com.example.trablho_prog_web.repository.GrupoRepository;
import com.example.trablho_prog_web.service.GrupoService;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "postagem")
public class Postagem {

    @Getter
    @Id
    @Column
    private String id;

    @Getter
    @NotBlank(message = "O título não pode estar vazio")
    @Size(min = 5, max = 100, message = "O título deve ter entre 5 e 100 caracteres")
    @Column(nullable = false)
    private String titulo;

    @Getter
    @NotBlank(message = "O texto da postagem não pode estar vazio")
    @Size(min = 10, message = "O texto deve ter pelo menos 10 caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Getter
    @NotNull(message = "O autor da postagem deve ser informado")
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Getter
    @Column(nullable = false)
    private boolean ativo = true;

    @OneToMany
    @JoinColumn(name = "postagem_id")
    private List<Comentario> comentarios = new ArrayList<Comentario>();

    @Getter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    public Postagem() {
    }

    public Postagem(String titulo, String texto, Usuario autor, Grupo grupo) {
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
        this.grupo = grupo;
        grupo.adicionarPostagem(this);
    }

    public Postagem(String id, String titulo, String texto, Usuario autor, Grupo grupo) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
        this.grupo = grupo;
        grupo.adicionarPostagem(this);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Comentario> getComentarios() {
        return Collections.unmodifiableList(comentarios);
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void adicionarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }
}