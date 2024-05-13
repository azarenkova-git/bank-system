package com.example.bank.app.repositories;

import com.example.bank.app.entities.DebitBankAccount;
import com.example.bank.utils.xml.XmlPersistenceService;
import org.springframework.stereotype.Component;

@Component
public class DebitBankAccountRepository extends AbstractEntityRepository<DebitBankAccount> {
    public DebitBankAccountRepository(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, DebitBankAccount.class);
    }
}
