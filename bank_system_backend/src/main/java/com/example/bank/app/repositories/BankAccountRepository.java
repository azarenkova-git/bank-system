package com.example.bank.app.repositories;

import com.example.bank.app.entities.AbstractBankAccount;
import com.example.bank.utils.xml.XmlPersistenceService;
import org.springframework.stereotype.Component;

@Component
public class BankAccountRepository extends AbstractEntityRepository<AbstractBankAccount> {
    public BankAccountRepository(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, AbstractBankAccount.class);
    }
}
