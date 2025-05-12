package com.example.trablho_prog_web.controller;

import com.example.trablho_prog_web.model.Postagem;
import com.example.trablho_prog_web.model.Usuario;
import com.example.trablho_prog_web.service.PostagemService;
import com.example.trablho_prog_web.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "home")
public class MainViewerController {

    private final PostagemService postagemService;
    private final UsuarioService usuarioService;

    public MainViewerController(PostagemService postagemService, UsuarioService usuarioService) {
        this.postagemService = postagemService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/") // http://localhost:8080/home/
    public String getHome(Model model) {
        model.addAttribute("contrudo", "Exibir valor na template");
        return "index";
    }

    @GetMapping("/login") // http://localhost:8080/home/login
    public String getLogin() {
        return "login";
    }

    @GetMapping("/cadastro") // http://localhost:8080/usuarios/cadastro
    public String getCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
        usuario.setId(UUID.randomUUID().toString());
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("usuario", usuario);
            return "cadastro"; // Retorna ao formulário se houver erros
        }
        usuarioService.salvar(usuario);
        return "redirect:/home/";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Postagem> listaDePosts = postagemService.listarTodas();
        System.out.println("Lista de posts bruta: " + listaDePosts);
        List<Postagem> listaFiltrada = listaDePosts.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println("Lista filtrada: " + listaFiltrada);
        model.addAttribute("posts", listaFiltrada);
        return "posts";
    }

    @GetMapping("/postar") // http://localhost:8080/home/postar
    public String getPostar() {
        return "postar";
    }
}
