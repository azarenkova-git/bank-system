package com.example.bank.app.controllers;

import com.example.bank.app.dto.DepositBankAccountDto;
import com.example.bank.app.entities.DepositBankAccount;
import com.example.bank.app.services.DepositBankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deposit-bank-account")
public class DepositBankAccountController {
    private final DepositBankAccountService depositBankAccountService;

    public DepositBankAccountController(DepositBankAccountService depositBankAccountService) {
        this.depositBankAccountService = depositBankAccountService;
    }

    @GetMapping("find/{id}")
    public DepositBankAccount find(@PathVariable String id) {
        return depositBankAccountService.find(id);
    }

    @PostMapping("create")
    public DepositBankAccount create(@RequestBody DepositBankAccountDto depositBankAccountDto) {
        return depositBankAccountService.create(depositBankAccountDto);
    }

    @PutMapping("update")
    public DepositBankAccount update(@RequestBody DepositBankAccountDto depositBankAccountDto) {
        return depositBankAccountService.update(depositBankAccountDto);
    }
}
