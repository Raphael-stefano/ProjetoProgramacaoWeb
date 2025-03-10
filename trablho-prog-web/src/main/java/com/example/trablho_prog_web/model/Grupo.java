package com.example.trablho_prog_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @Column
    private String id;

    @ManyToOne
    @JoinColumn(name = "main_admin_id", nullable = false)
    private Usuario mainAdmin;

    @ManyToMany
    @JoinTable(
            name = "grupo_usuarios",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;

    @ManyToMany
    @JoinTable(
            name = "grupo_posts",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "postagem_id")
    )
    private List<Postagem> posts;

    @ManyToMany
    @JoinTable(
            name = "grupo_admins",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> admins;
}
