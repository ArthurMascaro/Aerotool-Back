package edu.br.ifsp.domain.entities.tools;

public enum ToolSituation {
    BROKEN(0),
    LOST(1),
    BUSY(2),
    FREE(3);

    private int situationCode;

    ToolSituation(int situationCode) {
        this.situationCode = situationCode;
    }

    public int getSituationCode() {
        return situationCode;
    }

    public void setSituationCode(int situationCode) {
        this.situationCode = situationCode;
    }
}
