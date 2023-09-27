package edu.br.ifsp.web.model.event.response;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.event.EventSituation;
import edu.br.ifsp.domain.entities.event.EventType;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.web.model.user.response.UserResponse;

import java.sql.Timestamp;
import java.util.UUID;

public class EventResponse {

    private UUID id;
    private UserResponse responsible;
    private UserResponse subject;
    private Timestamp date;
    private EventSituation situation;
    private EventType type;

    public EventResponse(UUID id, UserResponse responsible, UserResponse subject, Timestamp date, EventSituation situation, EventType type) {
        this.id = id;
        this.responsible = responsible;
        this.subject = subject;
        this.date = date;
        this.situation = situation;
        this.type = type;
    }

    public static EventResponse fromEvent(Event event){
        return new EventResponse(event.getId(), UserResponse.fromUser(event.getResponsible()),
                UserResponse.fromUser(event.getSubject()), event.getDate(), event.getSituation(),
                event.getType());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserResponse getResponsible() {
        return responsible;
    }

    public void setResponsible(UserResponse responsible) {
        this.responsible = responsible;
    }

    public UserResponse getSubject() {
        return subject;
    }

    public void setSubject(UserResponse subject) {
        this.subject = subject;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public EventSituation getSituation() {
        return situation;
    }

    public void setSituation(EventSituation situation) {
        this.situation = situation;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}
