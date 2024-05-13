package com.example.bank.app.dto;

public class DepositDto {
    private String bankAccountId;
    private Long amountInCents;

    public DepositDto() {
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setAmountInCents(Long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public Long getAmountInCents() {
        return amountInCents;
    }
}
