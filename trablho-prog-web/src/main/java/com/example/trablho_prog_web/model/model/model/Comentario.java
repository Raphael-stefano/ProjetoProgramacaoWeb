package com.example.trablho_prog_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "O texto do comentário não pode estar vazio")
    @Size(min = 1, max = 500, message = "O comentário deve ter entre 1 e 500 caracteres")
    @Column(nullable = false, length = 500)
    private String texto;

    @NotNull(message = "O autor do comentário deve ser informado")
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Column(nullable = false)
    private boolean ativo = true; // Valor padrão

    @ManyToMany
    @JoinTable(
            name = "comentario_respostas",
            joinColumns = @JoinColumn(name = "comentario_id"),
            inverseJoinColumns = @JoinColumn(name = "resposta_id")
    )
    private List<Comentario> respostas;

    public Comentario() {
    }

    public Comentario(String texto, Usuario autor) {
        this.texto = texto;
        this.autor = autor;
    }

    public void adicionarResposta(Comentario resposta) {
        this.respostas.add(resposta);
    }
}