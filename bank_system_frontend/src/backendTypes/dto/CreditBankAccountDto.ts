import { AbstractBankAccountDto } from "./AbstractBankAccountDto";

export interface CreditBankAccountDto extends AbstractBankAccountDto {
    commissionRate: number;
    creditLimitInCents: number;
}
