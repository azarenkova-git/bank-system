import { Formik } from "formik";
import { isNil } from "lodash";
import { useContext } from "react";
import { CreditBankAccountDto } from "../../../backendTypes/dto/CreditBankAccountDto";
import { Bank } from "../../../backendTypes/entities/Bank";
import { BankClient } from "../../../backendTypes/entities/BankClient";
import BankAccountType from "../../../backendTypes/enum/BankAccountType";
import FormLayout from "../../../components/FormLayout/FormLayout";
import { ISelectOption } from "../../../components/inputs/ICommonInputProps";
import NumericInput from "../../../components/inputs/NumericInput/NumericInput";
import SingleSelect from "../../../components/inputs/SingleSelect/SingleSelect";
import ModalContext from "../../../components/Modal/ModalContext";
import { ErrorType } from "../../../utils/errorType";
import HttpMethod from "../../../utils/httpMethod";
import { useGetQuery } from "../../../utils/useGetQuery";
import useMutationQuery from "../../../utils/useMutationQuery";

function validateCreateCreditBankAccountForm(dto: CreditBankAccountDto) {
    const errors: ErrorType<CreditBankAccountDto> = {};
    if (isNil(dto.bankId)) {
        errors.bankId = "Обязательное поле";
    }

    if (isNil(dto.bankClientId)) {
        errors.bankClientId = "Обязательное поле";
    }

    if (isNil(dto.commissionRate)) {
        errors.bankClientId = "Обязательное поле";
    }

    if (isNil(dto.creditLimitInCents)) {
        errors.creditLimitInCents = "Обязательное поле";
    } else if (dto.creditLimitInCents < 0) {
        errors.creditLimitInCents = "Лимит не может быть отрицательным";
    }

    if (isNil(dto.commissionRate)) {
        errors.commissionRate = "Обязательное поле";
    } else if (dto.commissionRate < 0) {
        errors.commissionRate = "Комиссия не может быть отрицательной";
    }

    return errors;
}

function CreateCreditBankAccountForm() {
    const closeModal = useContext(ModalContext);

    const [createCreditBankAccount, isLoading] = useMutationQuery<CreditBankAccountDto>(
        "credit-bank-account/create",
        HttpMethod.POST,
        closeModal
    );

    const [banks] = useGetQuery<Bank[]>("bank/find-all");
    const bankOptions: ISelectOption[] = banks
        ?.filter((bank) => bank.allowedBankAccountTypes.includes(BankAccountType.CREDIT))
        ?.map((bank) => ({ label: bank.bankName, value: bank.id }));

    const [bankClients] = useGetQuery<BankClient[]>("bank-client/find-all");
    const bankClientOptions: ISelectOption[] = bankClients?.map((bankClient) => ({
        label: `${bankClient.firstName} ${bankClient.lastName}`,
        value: bankClient.id,
    }));

    return (
        <Formik<CreditBankAccountDto>
            initialValues={{
                bankId: null,
                bankClientId: null,
                commissionRate: 10,
                creditLimitInCents: 1000,
            }}
            validate={validateCreateCreditBankAccountForm}
            onSubmit={createCreditBankAccount}
            validateOnMount
        >
            <FormLayout isDataLoading={isLoading} onCancel={closeModal}>
                <SingleSelect label="Банк" fieldId="bankId" options={bankOptions} />
                <SingleSelect label="Клиент" fieldId="bankClientId" options={bankClientOptions} />
                <NumericInput label="Кредитный лимит в центах" fieldId="creditLimitInCents" />
                <NumericInput label="Коммисия в десятых долях процента" fieldId="commissionRate" />
            </FormLayout>
        </Formik>
    );
}

export default CreateCreditBankAccountForm;
