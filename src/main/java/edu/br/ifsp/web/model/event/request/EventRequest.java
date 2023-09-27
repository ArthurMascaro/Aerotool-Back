package edu.br.ifsp.web.model.event.request;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.event.EventSituation;
import edu.br.ifsp.domain.entities.event.EventType;
import edu.br.ifsp.web.model.user.request.UserRequest;
import edu.br.ifsp.web.model.user.request.UserRequestEvent;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record EventRequest(UserRequestEvent responsible, UserRequestEvent subject, EventType type, String description) {

    public EventRequest(UserRequestEvent responsible, UserRequestEvent subject, EventType type, String description) {
        this.responsible = responsible;
        this.subject = subject;
        this.type = type;
        this.description = description;
    }

    public Event toEvent(){
        return new Event(UUID.randomUUID(), responsible.toUser(),
                subject.toUser(), Timestamp.from(Instant.from(LocalDate.now())),
                EventSituation.SENT, type, description);
    }
}
