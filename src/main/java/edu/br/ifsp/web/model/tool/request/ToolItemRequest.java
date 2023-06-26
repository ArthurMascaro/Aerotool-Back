package edu.br.ifsp.web.model.tool.request;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.tools.ToolSituation;

import java.util.UUID;

public record ToolItemRequest(String patrimony, UUID locateId, UUID toolId, ToolSituation toolSituation) {

    public ToolItemRequest(String patrimony, UUID locateId, UUID toolId, ToolSituation toolSituation) {
        this.patrimony = patrimony;
        this.locateId = locateId;
        this.toolId = toolId;
        this.toolSituation = toolSituation;
    }

    public ToolItem toToolItem() {
        return new ToolItem(patrimony, locateId, toolId, toolSituation);
    }
}
