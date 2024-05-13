package com.example.bank.utils.exceptions;

import com.example.bank.utils.enums.BankAccountType;
import org.springframework.http.HttpStatus;

public class ForbiddenAccountTypeException extends AbstractHttpException {
    public ForbiddenAccountTypeException(BankAccountType bankAccountType) {
        super(
                HttpStatus.METHOD_NOT_ALLOWED,
                String.format(
                        "Bank doesn't allow to create accounts of type %s",
                        bankAccountType
                )
        );
    }
}
