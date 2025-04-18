package com.example.trablho_prog_web.repository;

import com.example.trablho_prog_web.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, String> {
    List<Comentario> findByAtivoTrue();
    List<Comentario> findByAutor_id(String autorId);
}