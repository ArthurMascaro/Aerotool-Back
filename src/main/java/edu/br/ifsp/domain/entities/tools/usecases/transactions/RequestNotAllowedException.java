package edu.br.ifsp.domain.entities.tools.usecases.transactions;

public class RequestNotAllowedException extends RuntimeException{
    public RequestNotAllowedException(String message) {
        super(message);
    }
}
