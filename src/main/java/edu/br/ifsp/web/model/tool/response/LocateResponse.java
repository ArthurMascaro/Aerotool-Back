package edu.br.ifsp.web.model.tool.response;

import edu.br.ifsp.domain.entities.tools.Locate;

import java.util.UUID;

public class LocateResponse {

    private UUID id;
    private String name;
    private String description;

    public LocateResponse(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static LocateResponse fromLocate(Locate locate) {
        return new LocateResponse(locate.getId(), locate.getName(), locate.getDescription());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
