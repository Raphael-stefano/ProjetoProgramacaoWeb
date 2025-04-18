package com.example.trablho_prog_web.assembler;

import com.example.trablho_prog_web.controller.GrupoController;
import com.example.trablho_prog_web.model.Grupo;
import com.example.trablho_prog_web.representationModel.GrupoModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {

    public GrupoModelAssembler() {
        super(GrupoController.class, GrupoModel.class);
    }

    @Override
    public GrupoModel toModel(Grupo grupo) {
        GrupoModel model = new GrupoModel();
        model.setId(grupo.getId());
        model.setMainAdminId(grupo.getMainAdmin().getId());
        model.setUsuarioIds(grupo.getUsuarios().stream().map(u -> u.getId()).toList());
        model.setAdminIds(grupo.getAdmins().stream().map(u -> u.getId()).toList());
        model.setPostIds(grupo.getPosts().stream().map(p -> p.getId()).toList());

        model.add(linkTo(methodOn(GrupoController.class).buscarGrupoPorId(grupo.getId())).withSelfRel());
        model.add(linkTo(methodOn(GrupoController.class).listarTodosGrupos()).withRel("todos-grupos"));
        model.add(linkTo(methodOn(GrupoController.class).buscarGruposDoAdmin(grupo.getMainAdmin().getId())).withRel("grupos-do-admin"));

        return model;
    }
}
