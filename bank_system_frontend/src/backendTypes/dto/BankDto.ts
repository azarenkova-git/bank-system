import BankAccountType from "../enum/BankAccountType";
import { AbstractEntityDto } from "./AbstractEntityDto";

export interface BankDto extends AbstractEntityDto {
    bankName: string;
    allowedBankAccountTypes: BankAccountType[];
    operationLimit: number;
}
