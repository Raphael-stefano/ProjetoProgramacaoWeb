package com.example.trablho_prog_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @Column
    private String id;

    @Column(nullable = false, length = 500)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToMany
    @JoinTable(
            name = "comentario_respostas",
            joinColumns = @JoinColumn(name = "comentario_id"),
            inverseJoinColumns = @JoinColumn(name = "resposta_id")
    )
    private List<Comentario> comentarios;
}

