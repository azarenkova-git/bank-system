package com.example.bank.app.dto;

import java.util.Date;

public class DepositBankAccountDto extends AbstractBankAccountDto {
    private Date dueDate;

    public DepositBankAccountDto() {
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
