package com.example.bank.app.dto;

public class CreditBankAccountDto extends AbstractBankAccountDto {
    private long commissionRate;
    private long creditLimitInCents;

    public CreditBankAccountDto() {
    }

    public void setCommissionRate(long commissionRate) {
        this.commissionRate = commissionRate;
    }

    public long getCommissionRate() {
        return commissionRate;
    }

    public void setCreditLimitInCents(long creditLimitInCents) {
        this.creditLimitInCents = creditLimitInCents;
    }

    public long getCreditLimitInCents() {
        return creditLimitInCents;
    }
}
