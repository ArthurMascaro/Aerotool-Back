package edu.br.ifsp.domain.entities.event;

import edu.br.ifsp.domain.entities.user.User;

import java.sql.Timestamp;
import java.util.UUID;

public class Event {

    //Find specific variable types for entity's
    private UUID id;
    private User entity1;
    private User entity2;
    private Timestamp date;
    private EventSituation situation;
    private EventType type;
    private String description;

    public Event() {
        this.id = UUID.randomUUID();
    }

    public Event(UUID id) {
        this.id = id;
    }

    public Event(UUID id, User entity1, User entity2, Timestamp date, EventSituation situation, EventType type, String description) {
        this.id = id;
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.date = date;
        this.situation = situation;
        this.type = type;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getEntity1() {
        return entity1;
    }

    public void setEntity1(User entity1) {
        this.entity1 = entity1;
    }

    public User getEntity2() {
        return entity2;
    }

    public void setEntity2(User entity2) {
        this.entity2 = entity2;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", entity1=" + entity1 +
                ", entity2=" + entity2 +
                ", date=" + date +
                ", situation=" + situation +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
