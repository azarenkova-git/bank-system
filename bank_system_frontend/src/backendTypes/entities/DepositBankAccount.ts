import { AbstractBankAccount } from "./AbstractBankAccount";

export interface DepositBankAccount extends AbstractBankAccount {
    dueDate: Date;
}
