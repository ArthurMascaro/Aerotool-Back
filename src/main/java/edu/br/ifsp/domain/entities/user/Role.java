package edu.br.ifsp.domain.entities.user;

public enum Role {

    ADMIN("Administrador"),
    TEACHER("Professor"),
    STUDENT("Estudante");

    private String label;

    Role(String label) {
        this.label = label;
    }

}
