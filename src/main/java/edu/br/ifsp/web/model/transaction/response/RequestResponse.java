package edu.br.ifsp.web.model.transaction.response;

import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.user.User;

import java.sql.Timestamp;
import java.util.UUID;

public class RequestResponse {

    private UUID id;
    private Timestamp date;
    private User user;

    public RequestResponse(UUID id, Timestamp date, User user) {
        this.id = id;
        this.date = date;
        this.user = user;
    }

    public static RequestResponse fromRequest(Request request){
        return new RequestResponse(request.getId(), request.getDate(), request.getUser());
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
