import { AbstractBankAccount } from "./AbstractBankAccount";

export interface CreditBankAccount extends AbstractBankAccount {
    commissionRateIn10nthOfPercent: number;
    creditLimitInCents: number;
}
