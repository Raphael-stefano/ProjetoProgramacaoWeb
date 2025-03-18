package com.example.trablho_prog_web.controller;

import com.example.trablho_prog_web.model.Usuario;
import com.example.trablho_prog_web.service.UsuarioService;
import com.example.trablho_prog_web.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/buscar")
    public List<Usuario> buscarPorNome(@RequestParam String nome) {
        return service.findByNome(nome);
    }

}
