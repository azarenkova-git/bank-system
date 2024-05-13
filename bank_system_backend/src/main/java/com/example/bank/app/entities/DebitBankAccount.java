package com.example.bank.app.entities;

import com.example.bank.utils.enums.BankAccountType;
import com.example.bank.utils.exceptions.OperationIsNotAllowedException;
import com.example.bank.utils.xml.adapters.DebitBankAccountAdapter;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlJavaTypeAdapter(DebitBankAccountAdapter.class)
public class DebitBankAccount extends AbstractBankAccount {
    private DebitBankAccount() {
        super();
    }

    public DebitBankAccount(String id, Bank bank, BankClient bankClient) {
        super(id, bank, bankClient);
    }

    @Override
    public void checkIfWithdrawalIsPossible(long amountInCentsToDecrement) throws OperationIsNotAllowedException {
        super.checkIfWithdrawalIsPossible(amountInCentsToDecrement);
        if (this.getBalanceInCents() - amountInCentsToDecrement < 0) {
            throw new OperationIsNotAllowedException("Operation that amount is not allowed for debit account");
        }
    }

    @Override
    public BankAccountType getBankAccountType() {
        return BankAccountType.DEBIT;
    }
}
