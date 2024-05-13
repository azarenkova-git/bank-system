package com.example.bank.app.entities;

import com.example.bank.utils.enums.BankAccountType;
import com.example.bank.utils.exceptions.OperationIsNotAllowedException;
import com.example.bank.utils.xml.adapters.DepositBankAccountAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement
@XmlJavaTypeAdapter(DepositBankAccountAdapter.class)
public class DepositBankAccount extends AbstractBankAccount {
    @XmlElement
    private Date dueDate;

    public DepositBankAccount(String id, Bank bank, BankClient bankClient, Date dueDate) {
        super(id, bank, bankClient);
        this.dueDate = dueDate;
    }

    private DepositBankAccount() {
        super();
    }

    @Override
    public BankAccountType getBankAccountType() {
        return BankAccountType.DEPOSIT;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void updateFromDto(Date newDueDate) {
        this.dueDate = newDueDate;
    }

    @Override
    public void checkIfWithdrawalIsPossible(long amountInCentsToDecrement) throws OperationIsNotAllowedException {
        super.checkIfWithdrawalIsPossible(amountInCentsToDecrement);
        Date currentDate = new Date();
        if (currentDate.before(dueDate)) {
            throw new OperationIsNotAllowedException("Operation is not allowed for deposit account before due date");
        }
    }
}
