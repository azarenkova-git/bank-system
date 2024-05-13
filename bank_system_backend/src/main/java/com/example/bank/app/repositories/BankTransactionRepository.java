package com.example.bank.app.repositories;

import com.example.bank.app.entities.BankTransaction;
import com.example.bank.utils.xml.XmlPersistenceService;
import org.springframework.stereotype.Component;

@Component
public class BankTransactionRepository extends AbstractEntityRepository<BankTransaction> {
    public BankTransactionRepository(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, BankTransaction.class);
    }
}
