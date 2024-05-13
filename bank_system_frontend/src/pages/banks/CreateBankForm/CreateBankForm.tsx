import { Formik } from "formik";
import { isNil } from "lodash";
import { useContext } from "react";
import { BankDto } from "../../../backendTypes/dto/BankDto";
import BankAccountType from "../../../backendTypes/enum/BankAccountType";
import FormLayout from "../../../components/FormLayout/FormLayout";
import NumericInput from "../../../components/inputs/NumericInput/NumericInput";
import SimpleSelect from "../../../components/inputs/SimpleSelect/SimpleSelect";
import TextInput from "../../../components/inputs/TextInput/TextInput";
import ModalContext from "../../../components/Modal/ModalContext";
import enumToArray from "../../../utils/enumToArray";
import { ErrorType } from "../../../utils/errorType";
import HttpMethod from "../../../utils/httpMethod";
import useMutationQuery from "../../../utils/useMutationQuery";

function validateBankForm(dto: BankDto) {
    const errors: ErrorType<BankDto> = {};
    if (isNil(dto.bankName)) {
        errors.bankName = "Обязательное поле";
    }
    if (isNil(dto.operationLimit)) {
        errors.operationLimit = "Обязательное поле";
    }
    if (isNil(dto.allowedBankAccountTypes) || dto.allowedBankAccountTypes.length === 0) {
        errors.allowedBankAccountTypes = "Выберите как минимум одно";
    }
    return errors;
}

function CreateBankForm() {
    const closeModal = useContext(ModalContext);

    const [createBank, isLoading] = useMutationQuery<BankDto>("bank/create", HttpMethod.POST, closeModal);

    return (
        <Formik<BankDto>
            initialValues={{
                bankName: null,
                allowedBankAccountTypes: [],
                operationLimit: null,
            }}
            validate={validateBankForm}
            onSubmit={createBank}
            validateOnMount
        >
            <FormLayout isDataLoading={isLoading} onCancel={closeModal}>
                <TextInput label="Название банка" fieldId="bankName" />
                <NumericInput label="Лимит операций для сомнительных клиентов" fieldId="operationLimit" />
                <SimpleSelect
                    label="Разрешенные типы аккаунтов"
                    options={enumToArray(BankAccountType)}
                    fieldId="allowedBankAccountTypes"
                />
            </FormLayout>
        </Formik>
    );
}

export default CreateBankForm;
