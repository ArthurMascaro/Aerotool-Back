package edu.br.ifsp.web.model.tool.request;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.ToolType;

import java.util.Objects;

public record ToolRequest(String name, String description, ToolType type) {

    public ToolRequest(String name, String description, ToolType type) {
        this.name = Objects.requireNonNull(name, "Name must not be null!");
        if (name.isEmpty()) throw new IllegalArgumentException("Name must not be empty!");

        this.description = Objects.requireNonNull(description, "Description must not be null!");
        if(description.isEmpty()) throw new IllegalArgumentException("Description must not be empty!");

        this.type = Objects.requireNonNull(type, "Type must not be null!");
    }

    public Tool toTool(){
        return new Tool(name, description, type);
    }
}
