package edu.br.ifsp.domain.entities.tools.usecases.utils;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
