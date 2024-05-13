package com.example.bank.app.dto;

public class TransferDto {
    private long amountInCents;
    private String senderBankAccountId;
    private String receiverBankAccountId;

    public TransferDto() {
    }

    public void setAmountInCents(long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public long getAmountInCents() {
        return amountInCents;
    }

    public void setSenderBankAccountId(String senderBankAccountId) {
        this.senderBankAccountId = senderBankAccountId;
    }

    public String getSenderBankAccountId() {
        return senderBankAccountId;
    }

    public void setReceiverBankAccountId(String receiverBankAccountId) {
        this.receiverBankAccountId = receiverBankAccountId;
    }

    public String getReceiverBankAccountId() {
        return receiverBankAccountId;
    }
}
