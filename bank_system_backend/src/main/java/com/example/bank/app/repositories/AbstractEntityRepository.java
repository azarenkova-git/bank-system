package com.example.bank.app.repositories;

import com.example.bank.app.entities.AbstractEntity;
import com.example.bank.utils.xml.XmlPersistenceService;
import java.util.List;

public abstract class AbstractEntityRepository<T extends AbstractEntity> {
    private final XmlPersistenceService xmlPersistenceService;
    private final Class<T> clazz;

    public AbstractEntityRepository(XmlPersistenceService xmlPersistenceService, Class<T> clazz) {
        this.xmlPersistenceService = xmlPersistenceService;
        this.clazz = clazz;
    }

    public T find(String id) {
        return xmlPersistenceService.load(id, clazz);
    }

    public void save(T entity) {
        xmlPersistenceService.save(entity, entity.getClass());
    }

    public List<T> findAll() {
        return xmlPersistenceService.findAll(clazz);
    }
}
