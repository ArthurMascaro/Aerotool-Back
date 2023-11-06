package edu.br.ifsp.web.model.user.request;

import edu.br.ifsp.domain.entities.user.User;

public record UserUpdateRequest(String name, String password) {
    public UserUpdateRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User toUser(String promptuary) {
        return new User(name, promptuary, password);
    }

}
