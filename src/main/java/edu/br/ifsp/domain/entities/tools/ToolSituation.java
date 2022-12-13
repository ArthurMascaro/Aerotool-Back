package edu.br.ifsp.domain.entities.tools;

public enum ToolSituation {

    BROKEN("Quebrado"),
    LOST("Perdido"),
    BUSY("Em uso"),
    FREE("Livre");

    private String label;

    ToolSituation(String label) {
        this.label = label;
    }
}
