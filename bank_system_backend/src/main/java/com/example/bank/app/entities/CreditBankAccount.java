package com.example.bank.app.entities;

import com.example.bank.utils.enums.BankAccountType;
import com.example.bank.utils.exceptions.OperationIsNotAllowedException;
import com.example.bank.utils.xml.adapters.CreditBankAccountAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlJavaTypeAdapter(CreditBankAccountAdapter.class)
public class CreditBankAccount extends AbstractBankAccount {
    private static final long ONE_THOUSANDTH_OF_PERCENT = 1_000;

    @XmlElement
    private long commissionRateIn10nthOfPercent;

    @XmlElement
    private long creditLimitInCents;

    public CreditBankAccount(
            String id,
            Bank bank,
            BankClient bankClient,
            long commissionRateIn10nthOfPercent,
            long creditLimitInCents
    ) {
        super(
                id,
                bank,
                bankClient
        );
        this.commissionRateIn10nthOfPercent = commissionRateIn10nthOfPercent;
        this.creditLimitInCents = creditLimitInCents;
    }

    private CreditBankAccount() {
        super();
    }

    @Override
    public BankAccountType getBankAccountType() {
        return BankAccountType.CREDIT;
    }

    public long getCommissionRateIn10nthOfPercent() {
        return commissionRateIn10nthOfPercent;
    }

    public long getCreditLimitInCents() {
        return creditLimitInCents;
    }

    public void updateFromDto(
            long newCommissionRateIn10nthOfPercent,
            long newCreditLimitInCents
    ) {
        this.commissionRateIn10nthOfPercent = newCommissionRateIn10nthOfPercent;
        this.creditLimitInCents = newCreditLimitInCents;
    }

    @Override
    public void checkIfWithdrawalIsPossible(long amountInCentsToDecrement) throws OperationIsNotAllowedException {
        super.checkIfWithdrawalIsPossible(amountInCentsToDecrement);
        if (this.getBalanceInCents() - amountInCentsToDecrement < -this.creditLimitInCents) {
            throw new OperationIsNotAllowedException("Operation that amount is not allowed for credit limit");
        }
    }

    private void applyCommission(long amountInCentsToMove) {
        long commission = amountInCentsToMove * this.commissionRateIn10nthOfPercent / ONE_THOUSANDTH_OF_PERCENT;
        this.balanceInCents = this.getBalanceInCents() - commission;
    }

    @Override
    public void withdraw(long amountInCentsToDecrement, boolean applyFees) {
        if (this.getBalanceInCents() < 0 && applyFees) {
            this.applyCommission(amountInCentsToDecrement);
        }
        super.withdraw(amountInCentsToDecrement, applyFees);
    }

    @Override
    public void deposit(long amountInCentsToIncrement, boolean applyFees) {
        if (this.getBalanceInCents() < 0 && applyFees) {
            this.applyCommission(amountInCentsToIncrement);
        }
        super.deposit(amountInCentsToIncrement, applyFees);
    }
}
