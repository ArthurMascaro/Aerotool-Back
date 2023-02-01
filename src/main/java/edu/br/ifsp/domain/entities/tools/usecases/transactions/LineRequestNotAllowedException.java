package edu.br.ifsp.domain.entities.tools.usecases.transactions;

public class LineRequestNotAllowedException extends RuntimeException{
    public LineRequestNotAllowedException(String message) {
        super(message);
    }
}
