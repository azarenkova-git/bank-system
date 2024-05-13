package com.example.bank.utils.exceptions;

import org.springframework.http.HttpStatus;

public class OperationIsNotAllowedException extends AbstractHttpException {
    public OperationIsNotAllowedException(String message) {
        super(
                HttpStatus.METHOD_NOT_ALLOWED,
                message
        );
    }
}
