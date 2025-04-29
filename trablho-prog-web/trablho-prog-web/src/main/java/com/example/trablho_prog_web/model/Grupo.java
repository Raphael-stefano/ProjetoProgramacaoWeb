package com.example.trablho_prog_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @Column
    private String id;

    @NotNull(message = "O administrador principal do grupo deve ser informado")
    @ManyToOne
    @JoinColumn(name = "main_admin_id", nullable = false)
    private Usuario mainAdmin;

    @NotEmpty(message = "O grupo deve conter pelo menos um usuário")
    @ManyToMany
    @JoinTable(
            name = "grupo_usuarios",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "grupo")
    private List<Postagem> posts = new ArrayList<>();

    // ... outros métodos existentes

    public void adicionarPostagem(Postagem postagem) {
        posts.add(postagem);
    }

    @NotEmpty(message = "O grupo deve ter pelo menos um administrador")
    @ManyToMany
    @JoinTable(
            name = "grupo_admins",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> admins = new ArrayList<Usuario>();

    public Grupo() {
    }

    public Grupo(Usuario mainAdmin, List<Usuario> usuarios, List<Usuario> admins) {
        this.mainAdmin = mainAdmin;
        this.usuarios = usuarios;
        this.admins = admins;

        if (admins != null && !admins.contains(mainAdmin)) {
            this.admins.add(mainAdmin);
        }
    }
}