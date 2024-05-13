package com.example.bank.utils.xml.adapters;

import com.example.bank.app.entities.Bank;
import com.example.bank.utils.xml.XmlPersistenceService;

public class BankAdapter extends AbstractEntityAdapter<Bank> {
    public BankAdapter(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, Bank.class);
    }
}
