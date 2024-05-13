package com.example.bank.utils.xml.adapters;

import com.example.bank.app.entities.DebitBankAccount;
import com.example.bank.utils.xml.XmlPersistenceService;

public class DebitBankAccountAdapter extends AbstractEntityAdapter<DebitBankAccount> {
    public DebitBankAccountAdapter(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, DebitBankAccount.class);
    }
}
