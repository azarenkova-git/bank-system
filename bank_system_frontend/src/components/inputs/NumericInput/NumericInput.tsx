import { TextField } from "@mui/material";
import { useField } from "formik";
import { isNil } from "lodash";
import { ICommonInputProps } from "../ICommonInputProps";
import InputContainer from "../InputContainer/InputContainer";

interface INumericInputProps extends ICommonInputProps {}

function NumericInput({ fieldId, label }: INumericInputProps) {
    const [field, meta, helpers] = useField<number>({ name: fieldId });

    const error = meta.touched && meta.error;
    const preprocessedValue = isNil(field.value) ? "" : String(field.value);

    return (
        <InputContainer error={error}>
            <TextField
                type="number"
                label={label}
                onChange={(e) => {
                    const newValue = e.target.value;
                    const preprocessedNewValue = newValue ? Number(newValue) : null;
                    helpers.setValue(preprocessedNewValue);
                }}
                onBlur={() => helpers.setTouched(true)}
                value={preprocessedValue}
                error={Boolean(error)}
            />
        </InputContainer>
    );
}

export default NumericInput;
