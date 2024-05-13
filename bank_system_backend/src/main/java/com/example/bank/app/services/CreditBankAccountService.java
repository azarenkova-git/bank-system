package com.example.bank.app.services;

import com.example.bank.app.dto.CreditBankAccountDto;
import com.example.bank.app.entities.Bank;
import com.example.bank.app.entities.BankClient;
import com.example.bank.app.entities.CreditBankAccount;
import com.example.bank.app.repositories.BankClientRepository;
import com.example.bank.app.repositories.BankRepository;
import com.example.bank.app.repositories.CreditBankAccountRepository;
import com.example.bank.app.repositories.DebitBankAccountRepository;
import com.example.bank.app.repositories.DepositBankAccountRepository;
import com.example.bank.utils.enums.BankAccountType;
import org.springframework.stereotype.Service;

@Service
public class CreditBankAccountService {
    private final BankRepository bankRepository;
    private final BankClientRepository bankClientRepository;
    private final CreditBankAccountRepository creditBankAccountRepository;

    public CreditBankAccountService(
            BankRepository bankRepository,
            BankClientRepository bankClientRepository,
            DebitBankAccountRepository debitBankAccountRepository,
            CreditBankAccountRepository creditBankAccountRepository,
            DepositBankAccountRepository depositBankAccountRepository
    ) {
        this.bankRepository = bankRepository;
        this.bankClientRepository = bankClientRepository;
        this.creditBankAccountRepository = creditBankAccountRepository;
    }

    public CreditBankAccount find(String id) {
        return creditBankAccountRepository.find(id);
    }

    public CreditBankAccount create(CreditBankAccountDto creditBankAccountDto) {
        Bank bank = bankRepository.find(creditBankAccountDto.getBankId());
        bank.checkIfAccountTypeIfAllowed(BankAccountType.CREDIT);
        BankClient bankClient = bankClientRepository.find(creditBankAccountDto.getBankClientId());
        CreditBankAccount creditBankAccount = new CreditBankAccount(
                creditBankAccountDto.getId(),
                bank,
                bankClient,
                creditBankAccountDto.getCommissionRate(),
                creditBankAccountDto.getCreditLimitInCents()
        );
        creditBankAccountRepository.save(creditBankAccount);
        return creditBankAccount;
    }

    public CreditBankAccount update(CreditBankAccountDto creditBankAccountDto) {
        CreditBankAccount creditBankAccount = creditBankAccountRepository.find(
                creditBankAccountDto.getId()
        );
        creditBankAccount.updateFromDto(
                creditBankAccountDto.getCommissionRate(),
                creditBankAccountDto.getCreditLimitInCents()
        );
        creditBankAccountRepository.save(creditBankAccount);
        return creditBankAccount;
    }
}
