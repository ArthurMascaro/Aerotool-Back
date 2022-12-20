package edu.br.ifsp.domain.entities.user;

import javax.swing.*;

public class Promptuary {

    //alter login to value
    private String login;
    private String password;

    private Promptuary() {
    }

    public static void main(String[] args) {
        Promptuary p = Promptuary.valueOf("banana");
    }

    public static Promptuary valueOf(String value){

        if (!isValid(value)) throw new IllegalArgumentException("String value is not a valid Promptuary");

        return new Promptuary();
    }

    private static boolean isValid(String value) {
        return false;
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
