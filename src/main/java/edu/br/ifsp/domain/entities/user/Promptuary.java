package edu.br.ifsp.domain.entities.user;

import javax.swing.*;

public class Promptuary {

    //Search for a specific variable type
    private String login;
    private String password;

    public Promptuary() {
    }

    public Promptuary(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Promptuary{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
