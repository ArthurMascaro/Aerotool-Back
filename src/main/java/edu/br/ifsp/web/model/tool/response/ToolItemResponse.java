package edu.br.ifsp.web.model.tool.response;

import edu.br.ifsp.domain.entities.tools.Locate;
import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.tools.ToolSituation;

import java.util.UUID;

public class ToolItemResponse {

    private UUID id;
    private String patrimony;
    private UUID locateId;
    private UUID toolId;
    private ToolSituation toolSituation;

    public ToolItemResponse(UUID id, String patrimony, UUID locateId, UUID toolId, ToolSituation toolSituation) {
        this.id = id;
        this.patrimony = patrimony;
        this.locateId = locateId;
        this.toolId = toolId;
        this.toolSituation = toolSituation;
    }

    public static ToolItemResponse createFromToolItem(ToolItem toolItem) {
        return new ToolItemResponse(
                toolItem.getId(),
                toolItem.getPatrimony(),
                toolItem.getLocateId(),
                toolItem.getToolId(),
                toolItem.getSituation()
        );
    }

    public ToolItemResponse(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPatrimony() {
        return patrimony;
    }

    public void setPatrimony(String patrimony) {
        this.patrimony = patrimony;
    }

    public UUID getLocateId() {
        return locateId;
    }

    public void setLocateId(UUID locateId) {
        this.locateId = locateId;
    }

    public UUID getToolId() {
        return toolId;
    }

    public void setToolId(UUID toolId) {
        this.toolId = toolId;
    }

    public ToolSituation getToolSituation() {
        return toolSituation;
    }

    public void setToolSituation(ToolSituation toolSituation) {
        this.toolSituation = toolSituation;
    }

}
