package com.example.trablho_prog_web.controller;

import com.example.trablho_prog_web.model.Usuario;
import com.example.trablho_prog_web.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

}
