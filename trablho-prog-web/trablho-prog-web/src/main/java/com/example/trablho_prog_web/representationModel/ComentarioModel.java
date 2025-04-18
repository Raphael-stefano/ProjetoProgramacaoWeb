package com.example.trablho_prog_web.representationModel;

import com.example.trablho_prog_web.model.Comentario;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "comentarios")
public class ComentarioModel extends RepresentationModel<ComentarioModel> {
    private String id;
    private String texto;
    private boolean ativo;
    private String autorId;
    private String postagemId;

    public static ComentarioModel from(Comentario comentario) {
        ComentarioModel model = new ComentarioModel();
        model.setId(comentario.getId());
        model.setTexto(comentario.getTexto());
        model.setAtivo(comentario.isAtivo());
        model.setAutorId(comentario.getAutor().getId());
        model.setPostagemId(comentario.getPostagem().getId());
        return model;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public String getAutorId() { return autorId; }
    public void setAutorId(String autorId) { this.autorId = autorId; }

    public String getPostagemId() { return postagemId; }
    public void setPostagemId(String PostagemId) { this.postagemId = postagemId; }
}
