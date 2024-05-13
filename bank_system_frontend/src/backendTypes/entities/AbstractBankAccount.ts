import BankAccountType from "../enum/BankAccountType";
import { AbstractEntity } from "./AbstractEntity";
import { Bank } from "./Bank";
import { BankClient } from "./BankClient";

export interface AbstractBankAccount extends AbstractEntity {
    balanceInCents: number;
    bank: Bank;
    bankClient: BankClient;
    bankAccountType: BankAccountType;
}
