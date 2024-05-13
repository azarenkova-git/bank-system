import BankAccountType from "../enum/BankAccountType";
import BankTransactionStatus from "../enum/BankTransactionStatus";
import { AbstractBankAccount } from "./AbstractBankAccount";
import { AbstractEntity } from "./AbstractEntity";

export interface BankTransaction extends AbstractEntity {
    amountInCents: number;
    bankAccountReceiver: AbstractBankAccount;
    bankAccountSender: AbstractBankAccount;
    status: BankTransactionStatus;
    type: BankAccountType;
}
