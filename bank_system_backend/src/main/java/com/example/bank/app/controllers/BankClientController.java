package com.example.bank.app.controllers;

import com.example.bank.app.dto.BankClientDto;
import com.example.bank.app.entities.BankClient;
import com.example.bank.app.services.BankClientService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank-client")
public class BankClientController {
    private final BankClientService bankClientService;

    public BankClientController(BankClientService bankClientService) {
        this.bankClientService = bankClientService;
    }

    @PostMapping("create")
    public BankClient create(@RequestBody BankClientDto bankClientDto) {
        return bankClientService.create(bankClientDto);
    }

    @PutMapping("update/{id}")
    public BankClient update(@RequestBody BankClientDto bankClientDto, @PathVariable String id) {
        return bankClientService.update(id, bankClientDto);
    }

    @GetMapping("find/{id}")
    public BankClient find(@PathVariable String id) {
        return bankClientService.find(id);
    }

    @GetMapping("find-all")
    public List<BankClient> findAll() {
        return bankClientService.findAll();
    }
}
