package edu.br.ifsp.web.model.user.request;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;

import java.util.UUID;

public record UserRequest(String name, String promptuary, String password, String role) {

    public UserRequest(String name, String promptuary, String password, String role) {
        this.name = name;
        this.promptuary = promptuary;
        this.password = password;
        this.role = role;
    }

    public User toUser(){
        return new User(UUID.randomUUID(), name, Role.valueOf(role), promptuary, password);
    }
}
