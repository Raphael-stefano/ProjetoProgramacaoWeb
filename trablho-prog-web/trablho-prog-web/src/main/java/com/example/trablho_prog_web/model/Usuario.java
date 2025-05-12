package com.example.trablho_prog_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column
    private String id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!.])(?=\\S+$).*$",
            message = "Senha deve conter pelo menos 1 letra maiúscula, 1 minúscula, 1 número e 1 caractere especial"
    )
    @Column(nullable = false)
    private String senha;

    public Usuario() {}

    public Usuario(String id, String nome, String email, String senha) {
        this.id = id;
        this.nome = formatarNome(nome);
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = formatarNome(nome);
        this.email = email;
        this.senha = senha;
    }

    private String formatarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return nome;
        }

        String[] palavras = nome.trim().split("\\s+");
        StringBuilder nomeFormatado = new StringBuilder();

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                nomeFormatado
                        .append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return nomeFormatado.toString().trim();
    }
}