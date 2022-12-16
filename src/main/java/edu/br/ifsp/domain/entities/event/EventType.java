package edu.br.ifsp.domain.entities.event;

public enum EventType {

    NOTIFICATION("Notificação"),
    SYSTEM_LOG("Notificação de Sistema"),
    ACTION_USERS("Ação de Usuários");

    private String label;

    EventType(String label) {
        this.label = label;
    }
}
