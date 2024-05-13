package com.example.bank.app.controllers;

import com.example.bank.app.dto.DepositDto;
import com.example.bank.app.dto.TransferDto;
import com.example.bank.app.dto.WithdrawDto;
import com.example.bank.app.entities.BankTransaction;
import com.example.bank.app.services.BankTransactionService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank-transaction")
public class BankTransactionController {
    private final BankTransactionService bankTransactionService;

    public BankTransactionController(BankTransactionService bankTransactionService) {
        this.bankTransactionService = bankTransactionService;
    }

    @GetMapping("find/{id}")
    public BankTransaction find(@PathVariable String id) {
        return bankTransactionService.find(id);
    }

    @GetMapping("find-all")
    public List<BankTransaction> findAll() {
        return bankTransactionService.findAll();
    }

    @PostMapping("transfer")
    public BankTransaction transfer(@RequestBody TransferDto transferDto) {
        return bankTransactionService.transfer(transferDto);
    }

    @PostMapping("withdraw")
    public BankTransaction withdraw(@RequestBody WithdrawDto withdrawDto) {
        return bankTransactionService.withdraw(withdrawDto);
    }

    @PostMapping("deposit")
    public BankTransaction deposit(@RequestBody DepositDto depositDto) {
        return bankTransactionService.deposit(depositDto);
    }

    @PutMapping("reverse/{id}")
    public BankTransaction reverse(@PathVariable String id) {
        return bankTransactionService.reverse(id);
    }
}
