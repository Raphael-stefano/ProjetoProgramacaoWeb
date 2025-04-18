package com.example.trablho_prog_web.representationModel;

import com.example.trablho_prog_web.model.Postagem;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.stream.Collectors;

public class PostagemModel extends RepresentationModel<PostagemModel> {
    private String id;
    private String titulo;
    private String conteudo;
    private boolean ativo;
    private String autorId;
    private String grupoId;
    private List<String> comentarioIds;

    public PostagemModel() {}

    public PostagemModel(String id, String titulo, String conteudo, boolean ativo, String autorId, String grupoId , List<String> comentarioIds) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.ativo = ativo;
        this.autorId = autorId;
        this.grupoId = grupoId;
        this.comentarioIds = comentarioIds;
    }

    public static PostagemModel from(Postagem postagem) {
        return new PostagemModel(
                postagem.getId(),
                postagem.getTitulo(),
                postagem.getTexto(),
                postagem.isAtivo(),
                postagem.getAutor().getId(),
                postagem.getGrupo().getId(),
                postagem.getComentarios() != null
                        ? postagem.getComentarios().stream().map(c -> c.getId()).collect(Collectors.toList())
                        : null
        );
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public String getAutorId() { return autorId; }
    public void setAutorId(String autorId) { this.autorId = autorId; }

    public String getGrupoId() { return grupoId; }
    public void setGrupoId(String grupoId) { this.grupoId = grupoId; }

    public List<String> getComentarioIds() { return comentarioIds; }
    public void setComentarioIds(List<String> comentarioIds) { this.comentarioIds = comentarioIds; }
}
