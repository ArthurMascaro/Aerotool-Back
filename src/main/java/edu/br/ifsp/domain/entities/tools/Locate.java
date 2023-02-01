package edu.br.ifsp.domain.entities.tools;

import java.util.UUID;

public class Locate {

    private UUID id;
    private String name;
    private String description;

    public Locate(UUID id, String name,  String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Locate(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("ID can't be null.");
        this.id = id;
    }

    public Locate() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
