package com.example.trablho_prog_web.assembler;

import com.example.trablho_prog_web.controller.UsuarioController;
import com.example.trablho_prog_web.model.Usuario;
import com.example.trablho_prog_web.representationModel.UsuarioModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

    public UsuarioModelAssembler() {
        super(UsuarioController.class, UsuarioModel.class);
    }

    @Override
    public UsuarioModel toModel(Usuario usuario) {
        UsuarioModel usuarioModel = UsuarioModel.from(usuario);

        usuarioModel.add(
                linkTo(methodOn(UsuarioController.class).buscarUsuarioPorId(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listarTodosUsuarios()).withRel("usuarios"),
                linkTo(methodOn(UsuarioController.class).atualizarUsuario(usuario.getId(), usuario)).withRel("atualizar"),
                linkTo(methodOn(UsuarioController.class).deletarUsuario(usuario.getId())).withRel("excluir")
        );

        return usuarioModel;
    }
}
