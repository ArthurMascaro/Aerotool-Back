package edu.br.ifsp.domain.entities.tools;

import java.util.UUID;

public class ToolItem {

    private UUID id;
    private String patrimony;
    private UUID locateId;
    private UUID toolId;
    private ToolSituation situation;

    public ToolItem(){
        situation = ToolSituation.FREE;
        this.id = UUID.randomUUID();
    }

    public ToolItem(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("ID can't be null.");
        this.id = id;
    }

    public ToolItem(UUID id, String patrimony, UUID locateId, UUID toolId, ToolSituation situation) {
        this.id = id;
        this.patrimony = patrimony;
        this.locateId = locateId;
        this.toolId = toolId;
        this.situation = situation;
    }

    public ToolItem(String patrimony, UUID locateId, UUID toolId, ToolSituation situation) {
        this.patrimony = patrimony;
        this.locateId = locateId;
        this.toolId = toolId;
        this.situation = situation;
    }

    public ToolItem newInstanceWithId(UUID id){
        return new ToolItem(id, patrimony, locateId, toolId, situation);
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

    public ToolSituation getSituation() {
        return situation;
    }

    public void setSituation(ToolSituation situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "ToolItem{" +
                "id=" + id +
                ", patrimony='" + patrimony + '\'' +
                ", locateId=" + locateId +
                ", toolId=" + toolId +
                ", situation=" + situation +
                '}';
    }
}
