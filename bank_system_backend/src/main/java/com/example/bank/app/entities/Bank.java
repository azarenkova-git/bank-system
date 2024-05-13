package com.example.bank.app.entities;

import com.example.bank.utils.enums.BankAccountType;
import com.example.bank.utils.exceptions.ForbiddenAccountTypeException;
import com.example.bank.utils.xml.adapters.BankAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Set;

@XmlRootElement
@XmlJavaTypeAdapter(BankAdapter.class)
public class Bank extends AbstractEntity {
    @XmlElement
    private long operationLimitWithQuestionableClients;

    @XmlElement
    private Set<BankAccountType> allowedBankAccountTypes;

    @XmlElement
    private String bankName;

    public Bank(
            String id,
            long operationLimitWithQuestionableClients,
            Set<BankAccountType> allowedBankAccountTypes,
            String bankName
    ) {
        super(id);
        this.operationLimitWithQuestionableClients = operationLimitWithQuestionableClients;
        this.allowedBankAccountTypes = allowedBankAccountTypes;
        this.bankName = bankName;
    }

    private Bank() {
    }

    public String getBankName() {
        return bankName;
    }

    public long getOperationLimitWithQuestionableClients() {
        return operationLimitWithQuestionableClients;
    }

    public Set<BankAccountType> getAllowedBankAccountTypes() {
        return allowedBankAccountTypes;
    }

    public void updateFromDto(
            long newOperationLimitWithQuestionableClients,
            Set<BankAccountType> newAllowedBankAccountTypes,
            String newBankName
    ) {
        operationLimitWithQuestionableClients = newOperationLimitWithQuestionableClients;
        allowedBankAccountTypes = newAllowedBankAccountTypes;
        bankName = newBankName;
    }

    public void checkIfAccountTypeIfAllowed(BankAccountType bankAccountType) throws ForbiddenAccountTypeException {
        if (!allowedBankAccountTypes.contains(bankAccountType)) {
            throw new ForbiddenAccountTypeException(bankAccountType);
        }
    }
}
