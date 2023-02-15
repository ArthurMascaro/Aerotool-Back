package edu.br.ifsp.domain.entities.tools;

import java.util.UUID;

public class ToolItem {

    private UUID id;
    private String patrimony;
    private Locate locate;
    private Tool tool;
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

    public ToolItem(UUID id, String patrimony, Locate locate, Tool tool, ToolSituation situation) {
        this.id = id;
        this.patrimony = patrimony;
        this.locate = locate;
        this.tool = tool;
        this.situation = situation;
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

    public Locate getLocate() {
        return locate;
    }

    public void setLocate(Locate locate) {
        this.locate = locate;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
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
                ", locate=" + locate +
                ", tool=" + tool +
                ", situation=" + situation +
                '}';
    }
}
