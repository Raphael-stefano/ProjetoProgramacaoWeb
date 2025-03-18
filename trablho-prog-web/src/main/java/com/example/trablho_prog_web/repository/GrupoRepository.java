package com.example.trablho_prog_web.repository;

import com.example.trablho_prog_web.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, String> {

    @Query("""
        SELECT DISTINCT g FROM Grupo g
        LEFT JOIN g.usuarios u
        LEFT JOIN g.admins a
        WHERE u.id = :usuarioId OR a.id = :usuarioId
    """)
    List<Grupo> buscarGruposPorUsuario(@Param("usuarioId") String usuarioId);

    @Query("""
        SELECT DISTINCT g FROM Grupo g
        JOIN g.admins a
        WHERE a.id = :usuarioId
    """)
    List<Grupo> buscarGruposPorAdmin(@Param("usuarioId") String usuarioId);

}

