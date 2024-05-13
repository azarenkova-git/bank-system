package com.example.bank.app.services;

import com.example.bank.app.entities.AbstractBankAccount;
import com.example.bank.app.repositories.BankAccountRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<AbstractBankAccount> findAll() {
        return bankAccountRepository.findAll();
    }
}
