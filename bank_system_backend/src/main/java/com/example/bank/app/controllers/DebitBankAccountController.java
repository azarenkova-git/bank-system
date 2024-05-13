package com.example.bank.app.controllers;

import com.example.bank.app.dto.DebitBankAccountDto;
import com.example.bank.app.entities.DebitBankAccount;
import com.example.bank.app.services.DebitBankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("debit-bank-account")
public class DebitBankAccountController {
    private final DebitBankAccountService debitBankAccountService;

    public DebitBankAccountController(DebitBankAccountService debitBankAccountService) {
        this.debitBankAccountService = debitBankAccountService;
    }

    @GetMapping("find/{id}")
    public DebitBankAccount find(@PathVariable String id) {
        return debitBankAccountService.find(id);
    }

    @PostMapping("create")
    public DebitBankAccount create(@RequestBody DebitBankAccountDto debitBankAccountDto) {
        return debitBankAccountService.create(debitBankAccountDto);
    }

    @PutMapping("update")
    public DebitBankAccount update(@RequestBody DebitBankAccountDto debitBankAccountDto) {
        return debitBankAccountService.update(debitBankAccountDto);
    }
}
