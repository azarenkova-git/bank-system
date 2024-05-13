import { DatePicker } from "@mui/x-date-pickers";
import { useField } from "formik";
import { ICommonInputProps } from "../../ICommonInputProps";
import InputContainer from "../../InputContainer/InputContainer";

interface IDateSelectProps extends ICommonInputProps {}

function DateInput({ fieldId, label }: IDateSelectProps) {
    const [field, meta, helpers] = useField<Date>({
        name: fieldId,
    });

    const error = meta.touched && meta.error;
    const preprocessedValue = field.value ?? null;

    return (
        <InputContainer error={error}>
            <DatePicker
                value={preprocessedValue}
                label={label}
                onChange={(value) => {
                    helpers.setTouched(true);
                    helpers.setValue(value);
                }}
                // error={Boolean(error)}
            />
        </InputContainer>
    );
}

export default DateInput;
