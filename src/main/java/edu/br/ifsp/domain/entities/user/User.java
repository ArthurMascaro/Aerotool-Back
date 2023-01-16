package edu.br.ifsp.domain.entities.user;

import java.util.UUID;

public class User {

    private UUID id;
    private String nome;
    private Role role;
    private Promptuary promptuary;

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(UUID id) {
        this.id = id;
    }

    public User(UUID id, String nome, Role role, Promptuary promptuary) {
        this.id = id;
        this.nome = nome;
        this.role = role;
        this.promptuary = promptuary;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Promptuary getPromptuary() {
        return promptuary;
    }

    public void setPromptuary(Promptuary promptuary) {
        this.promptuary = promptuary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", role=" + role +
                ", promptuary=" + promptuary +
                '}';
    }
}
