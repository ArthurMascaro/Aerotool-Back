package edu.br.ifsp.domain.entities.transaction;

import java.sql.Timestamp;
import java.util.UUID;

public class Request {

    private UUID id;
    private Timestamp date;

    public Request() {
        this.id = UUID.randomUUID();
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public Request(UUID id) {
        this.id = id;
    }

    public Request(UUID id, Timestamp date) {
        this.id = id;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
