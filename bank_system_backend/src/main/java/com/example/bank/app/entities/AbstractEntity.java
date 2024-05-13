package com.example.bank.app.entities;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.UUID;

public abstract class AbstractEntity {
    @XmlElement()
    private String id;

    protected AbstractEntity() {
    }

    public AbstractEntity(String id) {
        this.id = id == null ? UUID.randomUUID().toString() : id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity other)) {
            return false;
        }

        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s(id=%s)", this.getClass().getSimpleName(), id);
    }
}
