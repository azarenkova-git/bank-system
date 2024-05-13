package com.example.bank.app.dto;

import com.example.bank.utils.enums.BankAccountType;
import java.util.Set;

public class BankDto extends AbstractEntityDto {
    private long operationLimit;
    private Set<BankAccountType> allowedBankAccountTypes;
    private String bankName;

    public BankDto() {
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setOperationLimit(long operationLimit) {
        this.operationLimit = operationLimit;
    }

    public long getOperationLimit() {
        return operationLimit;
    }

    public void setAllowedBankAccountTypes(Set<BankAccountType> allowedBankAccountTypes) {
        this.allowedBankAccountTypes = allowedBankAccountTypes;
    }

    public Set<BankAccountType> getAllowedBankAccountTypes() {
        return allowedBankAccountTypes;
    }
}
