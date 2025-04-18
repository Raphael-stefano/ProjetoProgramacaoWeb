package com.example.trablho_prog_web.representationModel;

import org.springframework.hateoas.RepresentationModel;
import java.util.List;

public class GrupoModel extends RepresentationModel<GrupoModel> {

    private String id;
    private String mainAdminId;
    private List<String> usuarioIds;
    private List<String> adminIds;
    private List<String> postIds;

    public GrupoModel() {}

    public GrupoModel(String id, String mainAdminId, List<String> usuarioIds, List<String> adminIds, List<String> postIds) {
        this.id = id;
        this.mainAdminId = mainAdminId;
        this.usuarioIds = usuarioIds;
        this.adminIds = adminIds;
        this.postIds = postIds;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMainAdminId() { return mainAdminId; }
    public void setMainAdminId(String mainAdminId) { this.mainAdminId = mainAdminId; }

    public List<String> getUsuarioIds() { return usuarioIds; }
    public void setUsuarioIds(List<String> usuarioIds) { this.usuarioIds = usuarioIds; }

    public List<String> getAdminIds() { return adminIds; }
    public void setAdminIds(List<String> adminIds) { this.adminIds = adminIds; }

    public List<String> getPostIds() { return postIds; }
    public void setPostIds(List<String> postIds) { this.postIds = postIds; }
}
