package com.example.trablho_prog_web.repository;

import com.example.trablho_prog_web.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}

