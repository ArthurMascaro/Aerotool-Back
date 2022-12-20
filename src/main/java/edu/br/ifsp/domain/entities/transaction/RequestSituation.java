package edu.br.ifsp.domain.entities.transaction;

public enum RequestSituation {

    WAITING("Esperando"),
    ACCEPTED("Aceito"),
    REJECTED("Rejeitado");

    private String label;

    RequestSituation(String label) {
        this.label = label;
    }
}
