package edu.br.ifsp.domain.usecases.transactions;

public class LineRequestNotAllowedException extends RuntimeException{
    public LineRequestNotAllowedException(String message) {
        super(message);
    }
}
