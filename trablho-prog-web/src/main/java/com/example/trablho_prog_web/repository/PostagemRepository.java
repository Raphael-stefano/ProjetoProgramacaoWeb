package com.example.trablho_prog_web.repository;

import com.example.trablho_prog_web.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, String> {
    List<Postagem> findByAtivoTrue();
}
