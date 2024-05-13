package com.example.bank.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class AbstractHttpException extends ResponseStatusException {
    public AbstractHttpException(HttpStatus code, String message) {
        super(code, message);
    }
}
