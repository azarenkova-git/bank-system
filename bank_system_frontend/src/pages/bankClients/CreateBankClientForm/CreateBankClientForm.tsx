import { Formik } from "formik";
import { isNil } from "lodash";
import { useContext } from "react";
import { BankClientDto } from "../../../backendTypes/dto/BankClientDto";
import FormLayout from "../../../components/FormLayout/FormLayout";
import NumericInput from "../../../components/inputs/NumericInput/NumericInput";
import TextInput from "../../../components/inputs/TextInput/TextInput";
import ModalContext from "../../../components/Modal/ModalContext";
import { ErrorType } from "../../../utils/errorType";
import HttpMethod from "../../../utils/httpMethod";
import useMutationQuery from "../../../utils/useMutationQuery";

function validateBankClientForm(dto: BankClientDto) {
    const errors: ErrorType<BankClientDto> = {};

    if (isNil(dto.firstName)) {
        errors.firstName = "Обязательное поле";
    }

    if (isNil(dto.lastName)) {
        errors.lastName = "Обязательное поле";
    }

    if (!isNil(dto.passportId) && dto.passportId < 0) {
        errors.passportId = "Должно быть положительным числом";
    }

    return errors;
}

function CreateBankClientForm() {
    const closeModal = useContext(ModalContext);

    const [createBankClient, isLoading] = useMutationQuery<BankClientDto>(
        "bank-client/create",
        HttpMethod.POST,
        closeModal
    );

    return (
        <Formik<BankClientDto>
            initialValues={{
                lastName: null,
                firstName: null,
                address: null,
                passportId: null,
            }}
            validate={validateBankClientForm}
            onSubmit={createBankClient}
            validateOnMount
        >
            <FormLayout isDataLoading={isLoading} onCancel={closeModal}>
                <TextInput label="Имя" fieldId="firstName" />
                <TextInput label="Фамилия" fieldId="lastName" />
                <TextInput label="Адрес" fieldId="address" />
                <NumericInput label="Номер паспорта" fieldId="passportId" />
            </FormLayout>
        </Formik>
    );
}

export default CreateBankClientForm;
