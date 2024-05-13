package com.example.bank.app.controllers;

import com.example.bank.app.dto.CreditBankAccountDto;
import com.example.bank.app.entities.CreditBankAccount;
import com.example.bank.app.services.CreditBankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-bank-account")
public class CreditBankAccountController {
    private final CreditBankAccountService creditBankAccountService;

    public CreditBankAccountController(CreditBankAccountService creditBankAccountService) {
        this.creditBankAccountService = creditBankAccountService;
    }

    @GetMapping("find/{id}")
    public CreditBankAccount find(@PathVariable String id) {
        return creditBankAccountService.find(id);
    }

    @PostMapping("create")
    public CreditBankAccount create(@RequestBody CreditBankAccountDto creditBankAccountDto) {
        return creditBankAccountService.create(creditBankAccountDto);
    }

    @PutMapping("update")
    public CreditBankAccount update(@RequestBody CreditBankAccountDto creditBankAccountDto) {
        return creditBankAccountService.update(creditBankAccountDto);
    }
}
