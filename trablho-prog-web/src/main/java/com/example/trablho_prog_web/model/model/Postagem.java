package com.example.trablho_prog_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "postagem")
public class Postagem {

    @Id
    @Column
    private String id;

    @NotBlank(message = "O título não pode estar vazio")
    @Size(min = 5, max = 100, message = "O título deve ter entre 5 e 100 caracteres")
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "O texto da postagem não pode estar vazio")
    @Size(min = 10, message = "O texto deve ter pelo menos 10 caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @NotNull(message = "O autor da postagem deve ser informado")
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Column(nullable = false)
    private boolean ativo = true;

    @OneToMany
    @JoinColumn(name = "postagem_id")
    private List<Comentario> comentarios;

    public Postagem() {
    }

    public Postagem(String titulo, String texto, Usuario autor) {
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
    }
}