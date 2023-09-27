package edu.br.ifsp.domain.entities.transaction;

import edu.br.ifsp.domain.entities.user.User;

import java.sql.Timestamp;
import java.util.UUID;

public class Request {

    private UUID id;
    private Timestamp date;
    private User user;

    public Request() {
        this.id = UUID.randomUUID();
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public Request(UUID id){
        this.id = id;
    }

    public Request(User user) {
        this.user = user;
        this.id = UUID.randomUUID();
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public Request(UUID id) {
        this.id = id;
    }

    public Request(UUID id, User user) {
        this.id = id;
        this.user = user;
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public Request(UUID id, Timestamp date, User user) {
        this.id = id;
        this.date = date;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser(){return user;}

    public void setUser(User user){this.user = user;}

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
