package com.example.bank.utils.xml.adapters;

import com.example.bank.app.entities.BankClient;
import com.example.bank.utils.xml.XmlPersistenceService;

public class BankClientAdapter extends AbstractEntityAdapter<BankClient> {
    public BankClientAdapter(XmlPersistenceService xmlPersistenceService) {
        super(xmlPersistenceService, BankClient.class);
    }
}
