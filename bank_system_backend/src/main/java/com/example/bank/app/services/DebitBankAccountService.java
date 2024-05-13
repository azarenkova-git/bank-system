package com.example.bank.app.services;

import com.example.bank.app.dto.DebitBankAccountDto;
import com.example.bank.app.entities.Bank;
import com.example.bank.app.entities.BankClient;
import com.example.bank.app.entities.DebitBankAccount;
import com.example.bank.app.repositories.BankClientRepository;
import com.example.bank.app.repositories.BankRepository;
import com.example.bank.app.repositories.DebitBankAccountRepository;
import com.example.bank.utils.enums.BankAccountType;
import org.springframework.stereotype.Service;

@Service
public class DebitBankAccountService {
    private final BankRepository bankRepository;
    private final BankClientRepository bankClientRepository;
    private final DebitBankAccountRepository debitBankAccountRepository;

    public DebitBankAccountService(
            BankRepository bankRepository,
            BankClientRepository bankClientRepository,
            DebitBankAccountRepository debitBankAccountRepository
    ) {
        this.bankRepository = bankRepository;
        this.bankClientRepository = bankClientRepository;
        this.debitBankAccountRepository = debitBankAccountRepository;
    }

    public DebitBankAccount find(String id) {
        return debitBankAccountRepository.find(id);
    }

    public DebitBankAccount create(DebitBankAccountDto debitBankAccountDto) {
        Bank bank = bankRepository.find(debitBankAccountDto.getBankId());
        bank.checkIfAccountTypeIfAllowed(BankAccountType.DEBIT);
        BankClient bankClient = bankClientRepository.find(debitBankAccountDto.getBankClientId());
        DebitBankAccount debitBankAccount = new DebitBankAccount(
                debitBankAccountDto.getId(),
                bank,
                bankClient
        );
        debitBankAccountRepository.save(debitBankAccount);
        return debitBankAccount;
    }

    public DebitBankAccount update(DebitBankAccountDto debitBankAccountDto) {
        return debitBankAccountRepository.find(debitBankAccountDto.getId());
    }

}
