package edu.br.ifsp.domain.entities.event;

public enum EventSituation {

    SENT("Emitido"),
    VISUALIZED("Visualizado"),
    NOT_VISUALIZED("Não Visualizado"),
    ACCEPTED("Aceito"),
    REJECTED("Rejeitado");

    private String label;

    EventSituation(String label) {
        this.label = label;
    }
}
