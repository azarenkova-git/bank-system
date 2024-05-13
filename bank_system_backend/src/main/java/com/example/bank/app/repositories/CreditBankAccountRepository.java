package com.example.bank.app.repositories;

import com.example.bank.app.entities.CreditBankAccount;
import com.example.bank.utils.xml.XmlPersistenceService;
import org.springframework.stereotype.Component;

@Component
public class CreditBankAccountRepository extends AbstractEntityRepository<CreditBankAccount> {
    public CreditBankAccountRepository(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, CreditBankAccount.class);
    }
}
