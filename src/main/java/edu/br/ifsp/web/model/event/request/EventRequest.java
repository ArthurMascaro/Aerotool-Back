package edu.br.ifsp.web.model.event.request;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.event.EventSituation;
import edu.br.ifsp.domain.entities.event.EventType;
import edu.br.ifsp.domain.entities.user.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventRequest(UUID responsibleId, UUID subjectId, String type, String description) {

    public EventRequest(UUID responsibleId, UUID subjectId, String type, String description) {
        this.responsibleId = responsibleId;
        this.subjectId = subjectId;
        this.type = type;
        this.description = description;
    }

    public Event toEvent(){
        return new Event(UUID.randomUUID(), User.createUserWithId(responsibleId),
                User.createUserWithId(subjectId), Timestamp.valueOf(LocalDateTime.now()),
                EventSituation.SENT, EventType.valueOf(type), description);
    }
}
