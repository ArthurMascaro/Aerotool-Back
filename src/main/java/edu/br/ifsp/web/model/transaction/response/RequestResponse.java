package edu.br.ifsp.web.model.transaction.response;

import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;

import java.sql.Timestamp;
import java.util.UUID;

public class RequestResponse {

    private UUID id;
    private Timestamp date;
    private UUID userId;
    private Role role;
    private String promptuary;
    private String name;

    public RequestResponse(UUID id, Timestamp date, UUID userId, Role role, String promptuary, String name) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.role = role;
        this.promptuary = promptuary;
        this.name = name;
    }

    public static RequestResponse fromRequest(Request request){
        return new RequestResponse(request.getId(), request.getDate(),
                request.getUser().getId(), request.getUser().getRole(),
                request.getUser().getPromptuary(), request.getUser().getName());
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPromptuary() {
        return promptuary;
    }

    public void setPromptuary(String promptuary) {
        this.promptuary = promptuary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
