import { TextField } from "@mui/material";
import { useField } from "formik";
import { ICommonInputProps } from "../ICommonInputProps";
import InputContainer from "../InputContainer/InputContainer";

interface ITextInputProps extends ICommonInputProps {}

function TextInput({ fieldId, label }: ITextInputProps) {
    const [field, meta, helpers] = useField({
        name: fieldId,
    });

    const error = meta.touched && meta.error;
    const preprocessedValue = field.value ?? "";

    return (
        <InputContainer error={error}>
            <TextField
                label={label}
                value={preprocessedValue}
                onChange={(e) => {
                    const newValue = e.target.value;
                    const preprocessedNewValue = newValue ? String(newValue) : null;
                    helpers.setValue(preprocessedNewValue);
                }}
                onBlur={() => helpers.setTouched(true)}
                error={Boolean(error)}
            />
        </InputContainer>
    );
}

export default TextInput;
