package edu.br.ifsp.domain.entities.user;

import java.util.regex.Pattern;

public class Promptuary {

    //alter login to value
    private String login;
    private String password;

    private Promptuary() {
    }

    private Promptuary(String login){
        this.login = login;
    }

    public static Promptuary valueOf(String login){

        if (!isValid(login)) throw new IllegalArgumentException("Login value is not a valid Promptuary");

        return new Promptuary(login);
    }

    private static boolean isValid(String login) {
        if (login == null) {
            return false;
        }

        return Pattern.matches("^(AR|SC|SP)\\d{6,7}$", login);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin (String login) {
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
