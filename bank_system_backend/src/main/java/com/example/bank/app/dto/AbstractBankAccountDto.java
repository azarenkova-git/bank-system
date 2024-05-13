package com.example.bank.app.dto;

public abstract class AbstractBankAccountDto extends AbstractEntityDto {
    private String bankId;
    private String bankClientId;

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankClientId(String bankClientId) {
        this.bankClientId = bankClientId;
    }

    public String getBankClientId() {
        return bankClientId;
    }
}
