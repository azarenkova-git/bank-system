package com.example.bank.app.services;

import com.example.bank.app.dto.BankDto;
import com.example.bank.app.entities.Bank;
import com.example.bank.app.repositories.BankRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Bank create(BankDto bankDto) {
        Bank bank = new Bank(
                bankDto.getId(),
                bankDto.getOperationLimit(),
                bankDto.getAllowedBankAccountTypes(),
                bankDto.getBankName()
        );
        bankRepository.save(bank);
        return bank;
    }

    public Bank find(String id) {
        return bankRepository.find(id);
    }

    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    public Bank update(String id, BankDto bankDto) {
        Bank bank = bankRepository.find(id);
        bank.updateFromDto(
                bankDto.getOperationLimit(),
                bankDto.getAllowedBankAccountTypes(),
                bankDto.getBankName()
        );
        bankRepository.save(bank);
        return bank;
    }
}
