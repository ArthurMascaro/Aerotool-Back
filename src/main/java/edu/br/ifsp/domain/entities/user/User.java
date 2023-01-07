package edu.br.ifsp.domain.entities.user;

import java.util.UUID;
import java.util.regex;

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

    public static boolean User(Promptuary promptuary){

        // Regex to check valid promptuary
        Promptuary regex = Promptuary.valueOf("^(AR|SC|SP)\d[0-9]{6,7}$");

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the promptuary is empty
        // return false
        if (promptuary == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given promptuary
        // and regular expression.
        Matcher m = p.matcher(promptuary);

        // Return if the promptuary
        // matched the ReGex
        return m.matches();
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
