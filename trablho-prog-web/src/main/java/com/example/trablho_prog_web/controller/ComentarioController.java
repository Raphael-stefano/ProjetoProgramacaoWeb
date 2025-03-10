package com.example.trablho_prog_web.controller;

import com.example.trablho_prog_web.model.Comentario;
import com.example.trablho_prog_web.repository.ComentarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("comentarios")
public class ComentarioController {

    private ComentarioRepository repository;

    public ComentarioController(ComentarioRepository repository) {
        this.repository = repository;
    }

}