package edu.br.ifsp.web.model.tool.response;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.ToolType;
import edu.br.ifsp.web.model.tool.request.ToolRequest;

import java.util.UUID;

public class ToolResponse {
    private UUID id;
    private String name;
    private String description;
    private ToolType type;

    public ToolResponse(UUID id, String name, String description, ToolType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(ToolType type) {
        this.type = type;
    }

    public static ToolResponse fromTool(Tool tool){
        return new ToolResponse(tool.getId(), tool.getName(), tool.getDescription(), tool.getType());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ToolType getType() {
        return type;
    }
}
