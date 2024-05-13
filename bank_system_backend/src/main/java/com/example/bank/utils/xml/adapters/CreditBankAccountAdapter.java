package com.example.bank.utils.xml.adapters;

import com.example.bank.app.entities.CreditBankAccount;
import com.example.bank.utils.xml.XmlPersistenceService;

public class CreditBankAccountAdapter extends AbstractEntityAdapter<CreditBankAccount> {
    public CreditBankAccountAdapter(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, CreditBankAccount.class);
    }
}
