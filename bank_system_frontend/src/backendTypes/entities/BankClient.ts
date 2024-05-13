import { AbstractEntity } from "./AbstractEntity";

export interface BankClient extends AbstractEntity {
    lastName: string;
    firstName: string;
    address?: string;
    passportId?: string;
}
