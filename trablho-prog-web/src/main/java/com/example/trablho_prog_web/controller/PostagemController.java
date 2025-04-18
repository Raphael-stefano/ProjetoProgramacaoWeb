package com.example.trablho_prog_web.controller;

import com.example.trablho_prog_web.assembler.PostagemModelAssembler;
import com.example.trablho_prog_web.model.Grupo;
import com.example.trablho_prog_web.model.Postagem;
import com.example.trablho_prog_web.representationModel.PostagemModel;
import com.example.trablho_prog_web.service.PostagemService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    private final PostagemService postagemService;
    private final PostagemModelAssembler assembler;

    public PostagemController(PostagemService postagemService, PostagemModelAssembler assembler) {
        this.postagemService = postagemService;
        this.assembler = assembler;
    }

    @PostMapping("/criar")
    public ResponseEntity<PostagemModel> criarPostagem(@RequestBody Postagem postagem) {
        postagem.setId(UUID.randomUUID().toString());
        Postagem novaPostagem = postagemService.salvar(postagem);
        PostagemModel postagemModel = assembler.toModel(novaPostagem);

        return ResponseEntity
                .created(postagemModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(postagemModel);
    }

    @PostMapping("/criar-varias")
    public ResponseEntity<CollectionModel<PostagemModel>> criarPostagensEmLote(@RequestBody List<Postagem> postagens) {
        List<PostagemModel> postagemModels = postagens.stream()
                .map(postagem -> {
                    postagem.setId(UUID.randomUUID().toString());
                    return assembler.toModel(postagemService.salvar(postagem));
                })
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(CollectionModel.of(postagemModels,
                        linkTo(methodOn(PostagemController.class).listarTodasPostagens()).withSelfRel()));
    }


    @GetMapping("/listar")
    public CollectionModel<PostagemModel> listarTodasPostagens() {
        List<PostagemModel> postagens = postagemService.listarTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(postagens,
                linkTo(methodOn(PostagemController.class).listarTodasPostagens()).withSelfRel());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PostagemModel> buscarPostagemPorId(@PathVariable String id) {
        return postagemService.buscarPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<PostagemModel> atualizarPostagem(
            @PathVariable String id,
            @RequestBody Postagem postagem) {
        return postagemService.atualizar(id, postagem)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirPostagem(@PathVariable String id) {
        if (postagemService.buscarPorId(id).isPresent()) {
            postagemService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/autor/{usuarioId}")
    public CollectionModel<PostagemModel> buscarPostagensPorAutor(@PathVariable String usuarioId) {
        List<PostagemModel> postagens = postagemService.buscarPorAutor(usuarioId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(postagens,
                linkTo(methodOn(PostagemController.class).buscarPostagensPorAutor(usuarioId)).withSelfRel());
    }

    @GetMapping("/titulo/{titulo}")
    public CollectionModel<PostagemModel> buscarPostagensPorTitulo(@PathVariable String titulo) {
        List<PostagemModel> postagens = postagemService.buscarPorTitulo(titulo).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(postagens,
                linkTo(methodOn(PostagemController.class).buscarPostagensPorTitulo(titulo)).withSelfRel());
    }

    @GetMapping("/ativas")
    public CollectionModel<PostagemModel> buscarPostagensAtivas() {
        List<PostagemModel> postagens = postagemService.buscarAtivas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(postagens,
                linkTo(methodOn(PostagemController.class).buscarPostagensAtivas()).withSelfRel());
    }

    @GetMapping("/inativas")
    public CollectionModel<PostagemModel> buscarPostagensInativas() {
        List<PostagemModel> postagens = postagemService.buscarInativas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(postagens,
                linkTo(methodOn(PostagemController.class).buscarPostagensInativas()).withSelfRel());
    }

    @PatchMapping("/ativar/{id}")
    public ResponseEntity<PostagemModel> ativarPostagem(@PathVariable String id) {
        return postagemService.ativarPostagem(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/desativar/{id}")
    public ResponseEntity<PostagemModel> desativarPostagem(@PathVariable String id) {
        return postagemService.desativarPostagem(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{postagemId}/comentarios-ativos")
    public CollectionModel<String> buscarComentariosAtivosPorPostagem(@PathVariable String postagemId) {
        List<String> comentariosAtivosIds = postagemService.buscarComentariosAtivosPorPostagem(postagemId)
                .stream()
                .map(comentario -> comentario.getId())
                .collect(Collectors.toList());

        return CollectionModel.of(comentariosAtivosIds,
                linkTo(methodOn(PostagemController.class).buscarComentariosAtivosPorPostagem(postagemId)).withSelfRel());
    }

    @GetMapping("/grupo/{grupoId}")
    public CollectionModel<PostagemModel> buscarPostagensPorGrupo(@PathVariable String grupoId) {
        List<PostagemModel> postagens = postagemService.buscarPorGrupo(grupoId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(postagens,
                linkTo(methodOn(PostagemController.class).buscarPostagensPorGrupo(grupoId)).withSelfRel());
    }
}
