package com.example.bank.app.services;

import com.example.bank.app.dto.DepositDto;
import com.example.bank.app.dto.TransferDto;
import com.example.bank.app.dto.WithdrawDto;
import com.example.bank.app.entities.AbstractBankAccount;
import com.example.bank.app.entities.BankTransaction;
import com.example.bank.app.repositories.BankAccountRepository;
import com.example.bank.app.repositories.BankTransactionRepository;
import com.example.bank.utils.enums.BankTransactionType;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BankTransactionService {
    private final BankTransactionRepository bankTransactionRepository;
    private final BankAccountRepository bankAccountRepository;

    public BankTransactionService(
            BankTransactionRepository bankTransactionRepository,
            BankAccountRepository bankAccountRepository
    ) {
        this.bankTransactionRepository = bankTransactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankTransaction find(String id) {
        return bankTransactionRepository.find(id);
    }

    public List<BankTransaction> findAll() {
        return bankTransactionRepository.findAll();
    }

    public BankTransaction transfer(TransferDto transferDto) {
        AbstractBankAccount bankAccountFrom = bankAccountRepository.find(transferDto.getSenderBankAccountId());
        AbstractBankAccount bankAccountTo = bankAccountRepository.find(transferDto.getReceiverBankAccountId());
        BankTransaction bankTransaction = new BankTransaction(
                transferDto.getAmountInCents(),
                BankTransactionType.TRANSFER,
                bankAccountFrom,
                bankAccountTo
        );
        bankTransactionRepository.save(bankTransaction);
        try {
            bankTransaction.process();
        } finally {
            bankTransactionRepository.save(bankTransaction);
            bankAccountRepository.save(bankAccountFrom);
            bankAccountRepository.save(bankAccountTo);
        }
        return bankTransaction;
    }

    public BankTransaction withdraw(WithdrawDto withdrawDto) {
        AbstractBankAccount bankAccountFrom = bankAccountRepository.find(withdrawDto.getBankAccountId());
        BankTransaction bankTransaction = new BankTransaction(
                withdrawDto.getAmountInCents(),
                BankTransactionType.WITHDRAWAL,
                bankAccountFrom,
                null
        );
        bankTransactionRepository.save(bankTransaction);
        try {
            bankTransaction.process();
        } finally {
            bankTransactionRepository.save(bankTransaction);
            bankAccountRepository.save(bankAccountFrom);
        }
        return bankTransaction;
    }

    public BankTransaction deposit(DepositDto depositDto) {
        AbstractBankAccount bankAccountTo = bankAccountRepository.find(depositDto.getBankAccountId());
        BankTransaction bankTransaction = new BankTransaction(
                depositDto.getAmountInCents(),
                BankTransactionType.DEPOSIT,
                null,
                bankAccountTo
        );
        bankTransactionRepository.save(bankTransaction);
        try {
            bankTransaction.process();
        } finally {
            bankTransactionRepository.save(bankTransaction);
            bankAccountRepository.save(bankAccountTo);
        }
        return bankTransaction;
    }

    public BankTransaction reverse(String id) {
        BankTransaction bankTransaction = bankTransactionRepository.find(id);
        bankTransaction.reverse();
        bankTransactionRepository.save(bankTransaction);
        return bankTransaction;
    }
}
