package com.example.bank.app.dto;

public class WithdrawDto {
    private long amountInCents;
    private String bankAccountId;

    public WithdrawDto() {
    }

    public void setAmountInCents(long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public long getAmountInCents() {
        return amountInCents;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }
}
