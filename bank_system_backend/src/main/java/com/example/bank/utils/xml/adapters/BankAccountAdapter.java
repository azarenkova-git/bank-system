package com.example.bank.utils.xml.adapters;

import com.example.bank.app.entities.AbstractBankAccount;
import com.example.bank.utils.xml.XmlPersistenceService;

public class BankAccountAdapter extends AbstractEntityAdapter<AbstractBankAccount> {
    public BankAccountAdapter(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, AbstractBankAccount.class);
    }
}
