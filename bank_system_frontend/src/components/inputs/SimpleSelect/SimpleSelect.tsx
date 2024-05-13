import { InputLabel, MenuItem, Select } from "@mui/material";
import { useField } from "formik";
import { ICommonInputProps } from "../ICommonInputProps";
import InputContainer from "../InputContainer/InputContainer";

interface ISimpleSelectProps extends ICommonInputProps {
    options: string[];
}

function SimpleSelect({ fieldId, label, options }: ISimpleSelectProps) {
    const [field, meta, helpers] = useField({
        name: fieldId,
    });

    const error = meta.touched && meta.error;
    const preprocessedValue = field.value ?? undefined;

    return (
        <InputContainer error={error}>
            <InputLabel>{label}</InputLabel>
            <Select
                value={preprocessedValue}
                label={label}
                onChange={(e) => helpers.setValue(e.target.value)}
                onBlur={() => helpers.setTouched(true)}
                multiple
                error={Boolean(error)}
            >
                {options.map((option) => (
                    <MenuItem value={option} key={option}>
                        {option}
                    </MenuItem>
                ))}
            </Select>
        </InputContainer>
    );
}

export default SimpleSelect;
