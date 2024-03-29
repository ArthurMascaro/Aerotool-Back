package edu.br.ifsp.domain.entities.event;

import edu.br.ifsp.domain.entities.user.User;

import java.sql.Timestamp;
import java.util.UUID;

public class Event {

    private UUID id;
    private User responsible;
    private User subject;
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

    public Event(UUID id, User responsible, User subject, Timestamp date, EventSituation situation, EventType type, String description) {
        this.id = id;
        this.responsible = responsible;
        this.subject = subject;
        this.date = date;
        this.situation = situation;
        this.type = type;
        this.description = description;
    }

    public Event(UUID id, EventSituation situation) {
        this.id = id;
        this.situation = situation;
    }

    public static Event createWithIdAndSituation(UUID id, EventSituation situation){
        return new Event(id, situation);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public User getSubject() {
        return subject;
    }

    public void setSubject(User subject) {
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
                ", entity1=" + responsible +
                ", entity2=" + subject +
                ", date=" + date +
                ", situation=" + situation +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
