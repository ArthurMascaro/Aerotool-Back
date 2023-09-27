package edu.br.ifsp.web.model.user.request;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;

import java.util.UUID;

public record UserRequest(String name, Promptuary promptuary, Role role) {

    public UserRequest(String name, Promptuary promptuary, Role role) {
        this.name = name;
        this.promptuary = promptuary;
        this.role = role;
    }

    public User toUser(){
        return new User(UUID.randomUUID(), this.name, this.role, this.promptuary);
    }
}
