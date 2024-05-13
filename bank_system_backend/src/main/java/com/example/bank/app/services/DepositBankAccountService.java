package com.example.bank.app.services;

import com.example.bank.app.dto.DepositBankAccountDto;
import com.example.bank.app.entities.Bank;
import com.example.bank.app.entities.BankClient;
import com.example.bank.app.entities.DepositBankAccount;
import com.example.bank.app.repositories.BankClientRepository;
import com.example.bank.app.repositories.BankRepository;
import com.example.bank.app.repositories.DepositBankAccountRepository;
import com.example.bank.utils.enums.BankAccountType;
import org.springframework.stereotype.Service;

@Service
public class DepositBankAccountService {
    private final BankRepository bankRepository;
    private final BankClientRepository bankClientRepository;
    private final DepositBankAccountRepository depositBankAccountRepository;

    public DepositBankAccountService(
            BankRepository bankRepository,
            BankClientRepository bankClientRepository,
            DepositBankAccountRepository depositBankAccountRepository
    ) {
        this.bankRepository = bankRepository;
        this.bankClientRepository = bankClientRepository;
        this.depositBankAccountRepository = depositBankAccountRepository;
    }

    public DepositBankAccount find(String id) {
        return depositBankAccountRepository.find(id);
    }

    public DepositBankAccount create(DepositBankAccountDto depositBankAccountDto) {
        Bank bank = bankRepository.find(depositBankAccountDto.getBankId());
        bank.checkIfAccountTypeIfAllowed(BankAccountType.DEPOSIT);
        BankClient bankClient = bankClientRepository.find(depositBankAccountDto.getBankClientId());
        DepositBankAccount depositBankAccount = new DepositBankAccount(
                depositBankAccountDto.getId(),
                bank,
                bankClient,
                depositBankAccountDto.getDueDate()
        );
        depositBankAccountRepository.save(depositBankAccount);
        return depositBankAccount;
    }

    public DepositBankAccount update(DepositBankAccountDto depositBankAccountDto) {
        String depositBankAccountDtoId = depositBankAccountDto.getId();
        DepositBankAccount depositBankAccount = depositBankAccountRepository.find(depositBankAccountDtoId);
        depositBankAccount.updateFromDto(depositBankAccountDto.getDueDate());
        depositBankAccountRepository.save(depositBankAccount);
        return depositBankAccount;
    }
}
