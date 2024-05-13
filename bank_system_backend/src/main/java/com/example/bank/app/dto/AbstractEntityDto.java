package com.example.bank.app.dto;

public abstract class AbstractEntityDto {
    private String id;

    public AbstractEntityDto() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
