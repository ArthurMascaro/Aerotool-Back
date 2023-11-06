package edu.br.ifsp.web.model.user.request;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;

import java.util.UUID;

public record UserRequestEvent(UUID id, String name, String promptuary, Role role) {

    public UserRequestEvent(UUID id, String name, String promptuary, Role role) {
        this.id = id;
        this.name = name;
        this.promptuary = promptuary;
        this.role = role;
    }

    public User toUser(){
        return new User(id, name, role, promptuary);
    }
}
