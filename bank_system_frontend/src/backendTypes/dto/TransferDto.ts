import { AbstractEntityDto } from "./AbstractEntityDto";

export interface TransferDto extends AbstractEntityDto {
    amountInCents: number;
    senderBankAccountId: string;
    receiverBankAccountId: string;
}
