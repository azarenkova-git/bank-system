import { AbstractEntityDto } from "./AbstractEntityDto";

export interface AbstractBankAccountDto extends AbstractEntityDto {
    bankId: string;
    bankClientId: string;
}
