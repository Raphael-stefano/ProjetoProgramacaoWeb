package com.example.trablho_prog_web.repository;

import com.example.trablho_prog_web.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, String> {
}

