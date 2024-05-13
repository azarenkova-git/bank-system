import { Formik } from "formik";
import { isNil } from "lodash";
import { useContext } from "react";
import { TransferDto } from "../../../backendTypes/dto/TransferDto";
import { AbstractBankAccount } from "../../../backendTypes/entities/AbstractBankAccount";
import FormLayout from "../../../components/FormLayout/FormLayout";
import { ISelectOption } from "../../../components/inputs/ICommonInputProps";
import NumericInput from "../../../components/inputs/NumericInput/NumericInput";
import SingleSelect from "../../../components/inputs/SingleSelect/SingleSelect";
import ModalContext from "../../../components/Modal/ModalContext";
import { ErrorType } from "../../../utils/errorType";
import HttpMethod from "../../../utils/httpMethod";
import { useGetQuery } from "../../../utils/useGetQuery";
import useMutationQuery from "../../../utils/useMutationQuery";

function validateCreateTransferTransactionForm(dto: TransferDto) {
    const errors: ErrorType<TransferDto> = {};

    if (isNil(dto.senderBankAccountId)) {
        errors.senderBankAccountId = "Обязательное поле";
    }

    if (isNil(dto.receiverBankAccountId)) {
        errors.receiverBankAccountId = "Обязательное поле";
    }

    if (isNil(dto.amountInCents)) {
        errors.amountInCents = "Обязательное поле";
    } else if (dto.amountInCents <= 0) {
        errors.amountInCents = "Сумма должна быть больше нуля";
    }

    return errors;
}

function CreateTransferTransactionForm() {
    const closeModal = useContext(ModalContext);

    const [createBank, isLoading] = useMutationQuery<TransferDto>(
        "bank-transaction/transfer",
        HttpMethod.POST,
        closeModal
    );

    const [bankAccounts] = useGetQuery<AbstractBankAccount[]>("bank-account/find-all");
    const bankAccountOptions: ISelectOption[] = bankAccounts?.map((account) => ({
        label: `${account.bank.bankName} 
                ${account.bankAccountType} 
                ${account.bankClient.firstName} ${account.bankClient.lastName}`,
        value: account.id,
    }));

    return (
        <Formik<TransferDto>
            initialValues={{
                senderBankAccountId: null,
                receiverBankAccountId: null,
                amountInCents: 0,
            }}
            validate={validateCreateTransferTransactionForm}
            onSubmit={createBank}
            validateOnMount
        >
            <FormLayout isDataLoading={isLoading} onCancel={closeModal}>
                <SingleSelect
                    label="Банковский аккаунт отправителя"
                    fieldId="senderBankAccountId"
                    options={bankAccountOptions}
                />
                <SingleSelect
                    label="Банковский аккаунт получателя"
                    fieldId="receiverBankAccountId"
                    options={bankAccountOptions}
                />
                <NumericInput label="Размер операции в центах" fieldId="amountInCents" />
            </FormLayout>
        </Formik>
    );
}

export default CreateTransferTransactionForm;
