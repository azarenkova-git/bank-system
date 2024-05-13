package com.example.bank.utils.exceptions;

import com.example.bank.utils.enums.BankTransactionStatus;
import com.example.bank.utils.enums.BankTransactionType;
import org.springframework.http.HttpStatus;

public class TransactionIsNotReversableException extends AbstractHttpException {
    public TransactionIsNotReversableException(
            BankTransactionType type,
            BankTransactionStatus status
    ) {
        super(
                HttpStatus.METHOD_NOT_ALLOWED,
                String.format(
                        "Transaction of type %s with status %s is not cancellable",
                        type, status
                )
        );
    }

}
