import { AbstractEntityDto } from "./AbstractEntityDto";

export interface BankClientDto extends AbstractEntityDto {
    lastName: string;
    firstName: string;
    address: string;
    passportId: number;
}
