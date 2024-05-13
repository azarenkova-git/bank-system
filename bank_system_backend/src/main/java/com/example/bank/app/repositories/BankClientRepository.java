package com.example.bank.app.repositories;

import com.example.bank.app.entities.BankClient;
import com.example.bank.utils.xml.XmlPersistenceService;
import org.springframework.stereotype.Component;

@Component
public class BankClientRepository extends AbstractEntityRepository<BankClient> {
    public BankClientRepository(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, BankClient.class);
    }
}
