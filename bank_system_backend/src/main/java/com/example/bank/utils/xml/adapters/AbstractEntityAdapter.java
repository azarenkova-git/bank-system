package com.example.bank.utils.xml.adapters;

import com.example.bank.app.entities.AbstractEntity;
import com.example.bank.utils.xml.XmlPersistenceService;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public abstract class AbstractEntityAdapter<T extends AbstractEntity> extends XmlAdapter<String, T> {
    private final XmlPersistenceService xmlPersistenceService;
    private final Class<T> clazz;

    public AbstractEntityAdapter(
            XmlPersistenceService xmlPersistenceService,
            Class<T> clazz
    ) {
        this.xmlPersistenceService = xmlPersistenceService;
        this.clazz = clazz;
    }

    @Override
    public T unmarshal(String v) {
        return this.xmlPersistenceService.load(v, clazz);
    }

    @Override
    public String marshal(T v) {
        if (v == null) {
            return null;
        }
        return v.getId();
    }
}
