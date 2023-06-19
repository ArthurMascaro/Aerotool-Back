package edu.br.ifsp.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GenericResourceException extends RuntimeException {
    private final String title;

    public GenericResourceException(String message, String title) {
        super(message);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

