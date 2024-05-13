package com.example.bank.app.controllers;

import com.example.bank.app.dto.BankDto;
import com.example.bank.app.entities.Bank;
import com.example.bank.app.services.BankService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("create")
    public Bank create(@RequestBody BankDto bankDto) {
        return bankService.create(bankDto);
    }

    @PutMapping("update/{id}")
    public Bank update(@RequestBody BankDto bankDto, @PathVariable String id) {
        return bankService.update(id, bankDto);
    }

    @GetMapping("find/{id}")
    public Bank findOne(@PathVariable String id) {
        return bankService.find(id);
    }

    @GetMapping("find-all")
    public List<Bank> findAll() {
        return bankService.findAll();
    }
}
