package com.example.bank.app.repositories;

import com.example.bank.app.entities.DepositBankAccount;
import com.example.bank.utils.xml.XmlPersistenceService;
import org.springframework.stereotype.Component;

@Component
public class DepositBankAccountRepository extends AbstractEntityRepository<DepositBankAccount> {
    public DepositBankAccountRepository(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, DepositBankAccount.class);
    }
}
