package com.example.bank.app.entities;

import com.example.bank.utils.enums.BankTransactionStatus;
import com.example.bank.utils.enums.BankTransactionType;
import com.example.bank.utils.exceptions.AbstractHttpException;
import com.example.bank.utils.exceptions.OperationIsNotAllowedException;
import com.example.bank.utils.exceptions.TransactionIsNotReversableException;
import com.example.bank.utils.xml.adapters.BankTransactionAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlJavaTypeAdapter(BankTransactionAdapter.class)
public class BankTransaction extends AbstractEntity {
    @XmlElement
    private long amountInCents;

    @XmlElement
    private AbstractBankAccount bankAccountReceiver;

    @XmlElement
    private AbstractBankAccount bankAccountSender;

    @XmlElement
    private BankTransactionStatus status;

    @XmlElement
    private BankTransactionType type;

    public BankTransaction(
            long amountInCents,
            BankTransactionType type,
            AbstractBankAccount bankAccountReceiver,
            AbstractBankAccount bankAccountSender
    ) {
        super(null);
        this.type = type;
        this.status = BankTransactionStatus.IN_PROGRESS;
        this.amountInCents = amountInCents;
        this.bankAccountReceiver = bankAccountReceiver;
        this.bankAccountSender = bankAccountSender;
    }

    private BankTransaction() {
    }

    public AbstractBankAccount getBankAccountReceiver() {
        return bankAccountReceiver;
    }

    public long getAmountInCents() {
        return amountInCents;
    }

    public BankTransactionType getType() {
        return type;
    }

    public AbstractBankAccount getBankAccountSender() {
        return bankAccountSender;
    }

    public BankTransactionStatus getStatus() {
        return status;
    }

    public void process() throws OperationIsNotAllowedException {
        if (type != BankTransactionType.DEPOSIT) {
            try {
                bankAccountReceiver.checkIfWithdrawalIsPossible(amountInCents);
            } catch (AbstractHttpException e) {
                this.status = BankTransactionStatus.FAILED;
                throw e;
            }
        }

        if (type != BankTransactionType.WITHDRAWAL) {
            try {
                bankAccountSender.checkIfDepositIsPossible(amountInCents);
            } catch (AbstractHttpException e) {
                this.status = BankTransactionStatus.FAILED;
                throw e;
            }
        }

        if (type != BankTransactionType.DEPOSIT) {
            bankAccountReceiver.withdraw(amountInCents, true);
        }

        if (type != BankTransactionType.WITHDRAWAL) {
            bankAccountSender.deposit(amountInCents, true);
        }

        this.status = BankTransactionStatus.SUCCESS;
    }

    public void reverse() throws TransactionIsNotReversableException {
        if (type != BankTransactionType.TRANSFER) {
            throw new TransactionIsNotReversableException(type, status);
        }

        if (status != BankTransactionStatus.SUCCESS) {
            throw new TransactionIsNotReversableException(type, status);
        }

        bankAccountSender.withdraw(amountInCents, false);
        bankAccountReceiver.deposit(amountInCents, false);

        status = BankTransactionStatus.REVERTED;
    }
}
