package edu.br.ifsp.web.model.tool.request;

import edu.br.ifsp.domain.entities.tools.Locate;

public record LocateRequest(String name, String description) {
    public LocateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Locate toLocate() {
        return new Locate(null, name, description);
    }
}
