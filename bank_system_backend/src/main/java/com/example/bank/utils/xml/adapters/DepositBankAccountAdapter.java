package com.example.bank.utils.xml.adapters;

import com.example.bank.app.entities.DepositBankAccount;
import com.example.bank.utils.xml.XmlPersistenceService;

public class DepositBankAccountAdapter extends AbstractEntityAdapter<DepositBankAccount> {
    public DepositBankAccountAdapter(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, DepositBankAccount.class);
    }
}
