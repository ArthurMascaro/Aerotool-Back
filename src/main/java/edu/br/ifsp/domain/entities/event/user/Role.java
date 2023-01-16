package edu.br.ifsp.domain.entities.event.user;

public enum Role {

    ADMIN("Administrador"),
    TEACHER("Professor"),
    STUDENT("Estudante");

    private String label;

    Role(String label) {
        this.label = label;
    }

}
