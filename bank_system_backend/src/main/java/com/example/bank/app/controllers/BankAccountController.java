package com.example.bank.app.controllers;

import com.example.bank.app.entities.AbstractBankAccount;
import com.example.bank.app.services.BankAccountService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank-account")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("find-all")
    public List<AbstractBankAccount> findAll() {
        return bankAccountService.findAll();
    }
}
