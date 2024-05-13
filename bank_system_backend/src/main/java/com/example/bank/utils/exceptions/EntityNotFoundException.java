package com.example.bank.utils.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends AbstractHttpException {
    public EntityNotFoundException(String id) {
        super(
                HttpStatus.NOT_FOUND,
                String.format(
                        "Сущность с id %s не найдена",
                        id
                )
        );
    }
}
