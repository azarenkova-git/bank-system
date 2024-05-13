package com.example.bank.app.repositories;

import com.example.bank.app.entities.Bank;
import com.example.bank.utils.xml.XmlPersistenceService;
import org.springframework.stereotype.Component;

@Component
public class BankRepository extends AbstractEntityRepository<Bank> {
    public BankRepository(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, Bank.class);
    }
}
