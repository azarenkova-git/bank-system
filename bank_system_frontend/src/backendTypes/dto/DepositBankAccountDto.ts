import { AbstractBankAccountDto } from "./AbstractBankAccountDto";

export interface DepositBankAccountDto extends AbstractBankAccountDto {
    dueDate: Date;
}
