import BankAccountType from "../enum/BankAccountType";
import { AbstractEntity } from "./AbstractEntity";

export interface Bank extends AbstractEntity {
    operationLimitWithQuestionableClients: number;
    allowedBankAccountTypes: BankAccountType[];
    bankName: string;
}
