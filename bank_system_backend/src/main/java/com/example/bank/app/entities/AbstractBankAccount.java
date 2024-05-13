package com.example.bank.app.entities;

import com.example.bank.utils.enums.BankAccountType;
import com.example.bank.utils.exceptions.OperationIsNotAllowedException;
import com.example.bank.utils.xml.adapters.BankAccountAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(BankAccountAdapter.class)
public abstract class AbstractBankAccount extends AbstractEntity {
    @XmlElement
    protected long balanceInCents;

    @XmlElement
    private Bank bank;

    @XmlElement
    private BankClient bankClient;

    public AbstractBankAccount(String id, Bank bank, BankClient bankClient) {
        super(id);
        this.bank = bank;
        this.bankClient = bankClient;
        this.balanceInCents = 0;
    }

    protected AbstractBankAccount() {
        super();
    }

    public Bank getBank() {
        return bank;
    }

    public long getBalanceInCents() {
        return balanceInCents;
    }

    public BankClient getBankClient() {
        return bankClient;
    }

    public abstract BankAccountType getBankAccountType();

    private void checkIfTransferIsPossible(long amountInCentsToMove) throws OperationIsNotAllowedException {
        if (amountInCentsToMove < 0) {
            throw new OperationIsNotAllowedException("Operation with negative amount is not allowed");
        }

        if (this.bankClient.getHasLimitedOperations()
                && this.bank.getOperationLimitWithQuestionableClients() < amountInCentsToMove) {
            throw new OperationIsNotAllowedException("Operation limit exceeded"
                    + " clients due to bank policy");
        }
    }

    public void checkIfWithdrawalIsPossible(long amountInCentsToDecrement) throws OperationIsNotAllowedException {
        this.checkIfTransferIsPossible(amountInCentsToDecrement);
    }

    public void checkIfDepositIsPossible(long amountInCentsToIncrement) throws OperationIsNotAllowedException {
        this.checkIfTransferIsPossible(amountInCentsToIncrement);
    }

    public void deposit(long amountInCentsToIncrement, boolean applyFees) {
        this.balanceInCents += amountInCentsToIncrement;
    }

    public void withdraw(long amountInCentsToDecrement, boolean applyFees) {
        this.balanceInCents -= amountInCentsToDecrement;
    }
}
