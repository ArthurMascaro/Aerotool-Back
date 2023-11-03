package edu.br.ifsp.web.model.user.response;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;

import java.util.UUID;

public class UserResponse {

    UUID id;
    String name;
    Role role;
    String promptuary;

    public UserResponse(UUID id, String name, Role role, String promptuary) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.promptuary = promptuary;
    }

    public static UserResponse fromUser(User user){
        return new UserResponse(user.getId(), user.getNome(), user.getRole(), user.getPromptuary().getLogin());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
