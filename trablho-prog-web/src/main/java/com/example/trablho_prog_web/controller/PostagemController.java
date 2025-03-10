package com.example.trablho_prog_web.controller;

import com.example.trablho_prog_web.model.Postagem;
import com.example.trablho_prog_web.repository.PostagemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("postagens")
public class PostagemController {

    private PostagemRepository repository;

    public PostagemController(PostagemRepository repository) {
        this.repository = repository;
    }

}
