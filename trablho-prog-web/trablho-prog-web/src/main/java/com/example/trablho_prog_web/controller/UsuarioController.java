package com.example.trablho_prog_web.controller;

import com.example.trablho_prog_web.assembler.UsuarioModelAssembler;
import com.example.trablho_prog_web.model.Usuario;
import com.example.trablho_prog_web.repository.UsuarioRepository;
import com.example.trablho_prog_web.representationModel.UsuarioModel;
import com.example.trablho_prog_web.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler assembler;

    public UsuarioController(UsuarioService usuarioService, UsuarioModelAssembler assembler) {
        this.usuarioService = usuarioService;
        this.assembler = assembler;
    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioModel> criarUsuario(@RequestBody Usuario usuario) {
        usuario.setId(UUID.randomUUID().toString());
        Usuario novoUsuario = usuarioService.salvar(usuario);
        UsuarioModel usuarioModel = assembler.toModel(novoUsuario);

        return ResponseEntity
                .created(usuarioModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(usuarioModel);
    }

    @PostMapping("/criar-varios")
    public ResponseEntity<CollectionModel<UsuarioModel>> criarUsuarios(@RequestBody List<Usuario> usuarios) {
        List<UsuarioModel> modelos = usuarios.stream().map(usuario -> {
            usuario.setId(UUID.randomUUID().toString());
            return assembler.toModel(usuarioService.salvar(usuario));
        }).collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
                linkTo(methodOn(UsuarioController.class).listarTodosUsuarios()).withSelfRel()));
    }


    @GetMapping("/listar")
    public CollectionModel<UsuarioModel> listarTodosUsuarios() {
        List<UsuarioModel> usuarios = usuarioService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioController.class).listarTodosUsuarios()).withSelfRel());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioModel> buscarUsuarioPorId(@PathVariable String id) {
        return usuarioService.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public CollectionModel<UsuarioModel> buscarPorNome(@RequestParam String nome) {
        List<UsuarioModel> usuarios = usuarioService.findByNome(nome).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioController.class).buscarPorNome(nome)).withSelfRel());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioModel> atualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.atualizar(id, usuario);

        if (usuarioAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        UsuarioModel usuarioModel = assembler.toModel(usuarioAtualizado);
        return ResponseEntity.ok(usuarioModel);
    }


    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable String id) {
        if (usuarioService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
