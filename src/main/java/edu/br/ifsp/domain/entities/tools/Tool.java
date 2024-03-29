package edu.br.ifsp.domain.entities.tools;

import java.util.UUID;

public class Tool {

    private UUID id;
    private String name;
    private String description;
    private ToolType type;


    public Tool() {
        this.id = UUID.randomUUID();
    }

    public Tool(UUID id) {
        this.id = id;
    }

    public Tool(UUID id, String name, String description, ToolType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Tool(String name, String description, ToolType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Tool instanceWithId(UUID id){
        return new Tool(id, this.name, this.description, this.type);
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

    public ToolType getType() {
        return type;
    }

    public void setType(ToolType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
