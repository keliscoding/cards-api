package io.github.zam0k.cardsapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ResponseStatus(UNPROCESSABLE_ENTITY)
public class InvalidEntityAttributeException extends RuntimeException {
    public InvalidEntityAttributeException(String message) {
        super(message);
    }
}
