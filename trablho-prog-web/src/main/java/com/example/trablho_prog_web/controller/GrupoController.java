package com.example.trablho_prog_web.controller;

import com.example.trablho_prog_web.model.Grupo;
import com.example.trablho_prog_web.repository.GrupoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("grupos")
public class GrupoController {

    private GrupoRepository repository;

    public GrupoController(GrupoRepository repository) {
        this.repository = repository;
    }

}
