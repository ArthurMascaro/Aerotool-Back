package edu.br.ifsp.domain.entities.tools;

import javax.swing.*;

public enum ToolType {

    PROPERTY ("Patrimoniada"),
    COMMON ("DE CONSUMO");

    private String label;

    ToolType(String label){
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
