package edu.br.ifsp.domain.usecases.transactions;

public class RequestNotAllowedException extends RuntimeException{
    public RequestNotAllowedException(String message) {
        super(message);
    }
}
