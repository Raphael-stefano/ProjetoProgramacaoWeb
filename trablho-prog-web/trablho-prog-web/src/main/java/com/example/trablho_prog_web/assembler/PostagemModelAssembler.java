package com.example.trablho_prog_web.assembler;

import com.example.trablho_prog_web.controller.PostagemController;
import com.example.trablho_prog_web.model.Postagem;
import com.example.trablho_prog_web.representationModel.PostagemModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PostagemModelAssembler extends RepresentationModelAssemblerSupport<Postagem, PostagemModel> {

    public PostagemModelAssembler() {
        super(PostagemController.class, PostagemModel.class);
    }

    @Override
    public PostagemModel toModel(Postagem postagem) {
        PostagemModel postagemModel = PostagemModel.from(postagem);

        // Links básicos
        postagemModel.add(
                linkTo(methodOn(PostagemController.class)
                        .buscarPostagemPorId(postagem.getId())).withSelfRel(),
                linkTo(methodOn(PostagemController.class)
                        .listarTodasPostagens()).withRel("postagens"),
                linkTo(methodOn(PostagemController.class)
                        .buscarPostagensPorAutor(postagem.getAutor().getId())).withRel("postagens-do-autor"),
                linkTo(methodOn(PostagemController.class)
                        .buscarPostagensPorGrupo(postagem.getGrupo().getId())).withRel("postagens-do-grupo"), // Novo link
                linkTo(methodOn(PostagemController.class)
                        .atualizarPostagem(postagem.getId(), postagem)).withRel("atualizar"),
                linkTo(methodOn(PostagemController.class)
                        .excluirPostagem(postagem.getId())).withRel("excluir"),
                linkTo(methodOn(PostagemController.class)
                        .buscarComentariosAtivosPorPostagem(postagem.getId())).withRel("comentarios-ativos")
        );

        // Links condicionais
        if (postagem.isAtivo()) {
            postagemModel.add(
                    linkTo(methodOn(PostagemController.class)
                            .desativarPostagem(postagem.getId())).withRel("desativar"));
        } else {
            postagemModel.add(
                    linkTo(methodOn(PostagemController.class)
                            .ativarPostagem(postagem.getId())).withRel("ativar"));
        }

        return postagemModel;
    }
}