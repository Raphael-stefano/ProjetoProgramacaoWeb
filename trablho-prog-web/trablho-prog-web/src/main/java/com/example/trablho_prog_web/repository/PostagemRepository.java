package com.example.trablho_prog_web.repository;

import com.example.trablho_prog_web.model.Comentario;
import com.example.trablho_prog_web.model.Grupo;
import com.example.trablho_prog_web.model.Postagem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, String> {
    List<Postagem> findByAtivoTrue();
    List<Postagem> findByAtivoFalse();
    List<Postagem> findByAutorId(@Param("usuarioId") String usuarioId);
    List<Postagem> findByTitulo(@Param("titulo") String titulo);
    List<Postagem> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT p.comentarios FROM Postagem p WHERE p.id = :postagemId")
    List<Comentario> findComentariosByPostagemId(@Param("postagemId") String postagemId);

    @Query("SELECT c FROM Postagem p JOIN p.comentarios c WHERE p.id = :postagemId AND c.ativo = true")
    List<Comentario> findComentariosAtivosByPostagemId(@Param("postagemId") String postagemId);

    @Query("SELECT p.grupo FROM Postagem p WHERE p.id = :postagemId")
    Optional<Grupo> findGrupoByPostagemId(@Param("postagemId") String postagemId);

    List<Postagem> findByGrupoId(String grupoId);
}
