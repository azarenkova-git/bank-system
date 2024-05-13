package com.example.bank.app.services;

import com.example.bank.app.dto.BankClientDto;
import com.example.bank.app.entities.BankClient;
import com.example.bank.app.repositories.BankClientRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BankClientService {
    private final BankClientRepository bankClientRepository;

    public BankClientService(BankClientRepository bankClientRepository) {
        this.bankClientRepository = bankClientRepository;
    }

    public BankClient create(BankClientDto bankClientDto) {
        BankClient bankClient = new BankClient(
                bankClientDto.getId(),
                bankClientDto.getFirstName(),
                bankClientDto.getLastName(),
                bankClientDto.getAddress(),
                bankClientDto.getPassportId()
        );
        bankClientRepository.save(bankClient);
        return bankClient;
    }

    public BankClient find(String id) {
        return bankClientRepository.find(id);
    }

    public List<BankClient> findAll() {
        return bankClientRepository.findAll();
    }

    public BankClient update(String id, BankClientDto bankClientDto) {
        BankClient bankClient = bankClientRepository.find(id);
        bankClient.updateFromDto(
                bankClientDto.getFirstName(),
                bankClientDto.getLastName(),
                bankClientDto.getAddress(),
                bankClientDto.getPassportId()
        );
        bankClientRepository.save(bankClient);
        return bankClient;
    }
}
