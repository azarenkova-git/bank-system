import { AbstractEntityDto } from "./AbstractEntityDto";

export interface WithdrawDto extends AbstractEntityDto {
    bankAccountId: string;
    amountInCents: number;
}
