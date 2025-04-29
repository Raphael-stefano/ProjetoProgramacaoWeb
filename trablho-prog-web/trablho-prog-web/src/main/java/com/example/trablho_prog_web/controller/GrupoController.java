package com.example.trablho_prog_web.controller;

import com.example.trablho_prog_web.assembler.GrupoModelAssembler;
import com.example.trablho_prog_web.model.Grupo;
import com.example.trablho_prog_web.representationModel.GrupoModel;
import com.example.trablho_prog_web.service.GrupoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final GrupoService grupoService;
    private final GrupoModelAssembler assembler;

    public GrupoController(GrupoService grupoService, GrupoModelAssembler assembler) {
        this.grupoService = grupoService;
        this.assembler = assembler;
    }

    @PostMapping("/criar")
    public ResponseEntity<GrupoModel> criarGrupo(@RequestBody Grupo grupo) {
        grupo.setId(UUID.randomUUID().toString());
        Grupo novoGrupo = grupoService.salvar(grupo);
        GrupoModel grupoModel = assembler.toModel(novoGrupo);

        return ResponseEntity
                .created(grupoModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(grupoModel);
    }

    @PostMapping("/criar-varios")
    public ResponseEntity<CollectionModel<GrupoModel>> criarGrupos(@RequestBody List<Grupo> grupos) {
        List<GrupoModel> modelos = grupos.stream().map(grupo -> {
            grupo.setId(UUID.randomUUID().toString());
            return assembler.toModel(grupoService.salvar(grupo));
        }).collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
                linkTo(methodOn(GrupoController.class).listarTodosGrupos()).withSelfRel()));
    }


    @GetMapping("/listar")
    public CollectionModel<GrupoModel> listarTodosGrupos() {
        List<GrupoModel> grupos = grupoService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(grupos,
                linkTo(methodOn(GrupoController.class).listarTodosGrupos()).withSelfRel());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<GrupoModel> buscarGrupoPorId(@PathVariable String id) {
        return grupoService.buscarPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<GrupoModel> atualizarGrupo(@PathVariable String id, @RequestBody Grupo grupo) {
        Grupo grupoAtualizado = grupoService.atualizar(id, grupo).orElse(null);

        if (grupoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        GrupoModel grupoModel = assembler.toModel(grupoAtualizado);
        return ResponseEntity.ok(grupoModel);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirGrupo(@PathVariable String id) {
        if (grupoService.existePorId(id)) {
            grupoService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public CollectionModel<GrupoModel> buscarGruposDoUsuario(@PathVariable String usuarioId) {
        List<GrupoModel> grupos = grupoService.buscarGruposPorUsuario(usuarioId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(grupos,
                linkTo(methodOn(GrupoController.class).buscarGruposDoUsuario(usuarioId)).withSelfRel());
    }

    @GetMapping("/admin/{adminId}")
    public CollectionModel<GrupoModel> buscarGruposDoAdmin(@PathVariable String adminId) {
        List<GrupoModel> grupos = grupoService.buscarGruposPorAdmin(adminId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(grupos,
                linkTo(methodOn(GrupoController.class).buscarGruposDoAdmin(adminId)).withSelfRel());
    }
}
