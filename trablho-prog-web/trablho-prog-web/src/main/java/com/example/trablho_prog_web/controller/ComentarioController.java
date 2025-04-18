package com.example.trablho_prog_web.controller;

import com.example.trablho_prog_web.assembler.ComentarioModelAssembler;
import com.example.trablho_prog_web.model.Comentario;
import com.example.trablho_prog_web.representationModel.ComentarioModel;
import com.example.trablho_prog_web.service.ComentarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;
    private final ComentarioModelAssembler comentarioModelAssembler;

    public ComentarioController(ComentarioService comentarioService, ComentarioModelAssembler comentarioModelAssembler) {
        this.comentarioService = comentarioService;
        this.comentarioModelAssembler = comentarioModelAssembler;
    }

    @PostMapping("/comentar")
    public ResponseEntity<ComentarioModel> criarComentario(@RequestBody Comentario comentario) {
        comentario.setId(UUID.randomUUID().toString());
        Comentario novoComentario = comentarioService.criar(comentario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comentarioModelAssembler.toModel(novoComentario));
    }

    @PostMapping("/comentar-multiplos")
    public ResponseEntity<CollectionModel<ComentarioModel>> criarComentariosEmLote(@RequestBody List<Comentario> comentarios) {
        List<ComentarioModel> comentariosCriados = comentarios.stream().map(comentario -> {
            comentario.setId(UUID.randomUUID().toString());
            Comentario novo = comentarioService.criar(comentario);
            return comentarioModelAssembler.toModel(novo);
        }).toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(CollectionModel.of(comentariosCriados));
    }


    @GetMapping("/listar")
    public ResponseEntity<CollectionModel<ComentarioModel>> listarTodosComentarios() {
        List<Comentario> comentarios = comentarioService.listarTodos();
        return ResponseEntity.ok(
                CollectionModel.of(comentarioModelAssembler.toCollectionModel(comentarios),
                        linkTo(methodOn(ComentarioController.class).listarTodosComentarios()).withSelfRel())
        );
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ComentarioModel> buscarComentarioPorId(@PathVariable String id) {
        Optional<Comentario> comentario = comentarioService.findById(id);
        return comentario.map(value -> ResponseEntity.ok(comentarioModelAssembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ComentarioModel> atualizarComentario(
            @PathVariable String id,
            @RequestBody Comentario comentario) {
        Optional<Comentario> comentarioAtualizado = comentarioService.atualizar(id, comentario);
        return comentarioAtualizado.map(value -> ResponseEntity.ok(comentarioModelAssembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable String id) {
        if (comentarioService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<ComentarioModel>> buscarComentariosPorUsuario(@PathVariable String usuarioId) {
        List<Comentario> comentarios = comentarioService.findByUsuario(usuarioId);
        return ResponseEntity.ok(
                CollectionModel.of(comentarioModelAssembler.toCollectionModel(comentarios),
                        linkTo(methodOn(ComentarioController.class).buscarComentariosPorUsuario(usuarioId)).withSelfRel())
        );
    }
}
