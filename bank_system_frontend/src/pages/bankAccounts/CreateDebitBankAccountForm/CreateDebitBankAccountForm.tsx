import { Formik } from "formik";
import { isNil } from "lodash";
import { useContext } from "react";
import { CreditBankAccountDto } from "../../../backendTypes/dto/CreditBankAccountDto";
import { DebitBankAccountDto } from "../../../backendTypes/dto/DebitBankAccountDto";
import { Bank } from "../../../backendTypes/entities/Bank";
import { BankClient } from "../../../backendTypes/entities/BankClient";
import BankAccountType from "../../../backendTypes/enum/BankAccountType";
import FormLayout from "../../../components/FormLayout/FormLayout";
import { ISelectOption } from "../../../components/inputs/ICommonInputProps";
import SingleSelect from "../../../components/inputs/SingleSelect/SingleSelect";
import ModalContext from "../../../components/Modal/ModalContext";
import { ErrorType } from "../../../utils/errorType";
import HttpMethod from "../../../utils/httpMethod";
import { useGetQuery } from "../../../utils/useGetQuery";
import useMutationQuery from "../../../utils/useMutationQuery";

function validateCreateDebitBankAccountForm(dto: DebitBankAccountDto) {
    const errors: ErrorType<CreditBankAccountDto> = {};
    if (isNil(dto.bankId)) {
        errors.bankId = "Обязательное поле";
    }

    if (isNil(dto.bankClientId)) {
        errors.bankClientId = "Обязательное поле";
    }

    return errors;
}

function CreateDebitBankAccountForm() {
    const closeModal = useContext(ModalContext);

    const [createDebitBankAccount, isLoading] = useMutationQuery<DebitBankAccountDto>(
        "debit-bank-account/create",
        HttpMethod.POST,
        closeModal
    );

    const [banks] = useGetQuery<Bank[]>("bank/find-all");
    const bankOptions: ISelectOption[] = banks
        ?.filter((bank) => bank.allowedBankAccountTypes.includes(BankAccountType.DEBIT))
        ?.map((bank) => ({ label: bank.bankName, value: bank.id }));

    const [bankClients] = useGetQuery<BankClient[]>("bank-client/find-all");
    const bankClientOptions: ISelectOption[] = bankClients?.map((bankClient) => ({
        label: `${bankClient.firstName} ${bankClient.lastName}`,
        value: bankClient.id,
    }));

    return (
        <Formik<DebitBankAccountDto>
            initialValues={{
                bankId: null,
                bankClientId: null,
            }}
            validate={validateCreateDebitBankAccountForm}
            onSubmit={createDebitBankAccount}
            validateOnMount
        >
            <FormLayout isDataLoading={isLoading} onCancel={closeModal}>
                <SingleSelect label="Банк" fieldId="bankId" options={bankOptions} />
                <SingleSelect label="Клиент" fieldId="bankClientId" options={bankClientOptions} />
            </FormLayout>
        </Formik>
    );
}

export default CreateDebitBankAccountForm;
