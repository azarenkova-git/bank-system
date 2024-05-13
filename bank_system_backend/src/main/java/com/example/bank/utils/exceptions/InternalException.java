package com.example.bank.utils.exceptions;

import org.springframework.http.HttpStatus;

public class InternalException extends AbstractHttpException {
    public InternalException() {
        super(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Внутренняя ошибка сервера"
        );
    }
}
