package com.example.trablho_prog_web.model;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Column(nullable = false)
    private boolean ativo;

    @OneToMany
    @JoinColumn(name = "postagem_id")
    private List<Comentario> comentarios;
}
