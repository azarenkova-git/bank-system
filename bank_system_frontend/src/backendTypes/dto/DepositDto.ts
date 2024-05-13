import { AbstractEntityDto } from "./AbstractEntityDto";

export interface DepositDto extends AbstractEntityDto {
    bankAccountId: string;
    amountInCents: number;
}
