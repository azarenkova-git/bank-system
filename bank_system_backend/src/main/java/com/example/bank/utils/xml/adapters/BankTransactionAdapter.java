package com.example.bank.utils.xml.adapters;

import com.example.bank.app.entities.BankTransaction;
import com.example.bank.utils.xml.XmlPersistenceService;

public class BankTransactionAdapter extends AbstractEntityAdapter<BankTransaction> {
    public BankTransactionAdapter(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, BankTransaction.class);
    }
}
