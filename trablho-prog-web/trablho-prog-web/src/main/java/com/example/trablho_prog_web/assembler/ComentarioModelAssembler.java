package com.example.trablho_prog_web.assembler;

import com.example.trablho_prog_web.controller.ComentarioController;
import com.example.trablho_prog_web.model.Comentario;
import com.example.trablho_prog_web.representationModel.ComentarioModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ComentarioModelAssembler extends RepresentationModelAssemblerSupport<Comentario, ComentarioModel> {

    public ComentarioModelAssembler() {
        super(ComentarioController.class, ComentarioModel.class);
    }

    @Override
    public ComentarioModel toModel(Comentario comentario) {
        ComentarioModel comentarioModel = ComentarioModel.from(comentario);

        comentarioModel.add(
                linkTo(methodOn(ComentarioController.class).buscarComentarioPorId(comentario.getId())).withSelfRel(),
                linkTo(methodOn(ComentarioController.class).listarTodosComentarios()).withRel("comentarios"),
                linkTo(methodOn(ComentarioController.class).atualizarComentario(comentario.getId(), comentario)).withRel("atualizar"),
                linkTo(methodOn(ComentarioController.class).deletarComentario(comentario.getId())).withRel("excluir")
        );

        return comentarioModel;
    }
}


