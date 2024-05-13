import { InputLabel, MenuItem, Select } from "@mui/material";
import { useField } from "formik";
import { ICommonInputProps, ISelectOption } from "../ICommonInputProps";
import InputContainer from "../InputContainer/InputContainer";

interface ISingleSelectProps extends ICommonInputProps {
    options: ISelectOption[];
}

function SingleSelect({ fieldId, label, options }: ISingleSelectProps) {
    const [field, meta, helpers] = useField({
        name: fieldId,
    });

    const error = meta.touched && meta.error;
    const preprocessedValue = field.value ?? "";

    return (
        <InputContainer error={error}>
            <InputLabel>{label}</InputLabel>
            <Select
                value={preprocessedValue}
                label={label}
                onChange={(e) => helpers.setValue(e.target.value)}
                onBlur={() => helpers.setTouched(true)}
                error={Boolean(error)}
            >
                {options?.map((option) => (
                    <MenuItem value={option.value} key={option.value}>
                        {option.label}
                    </MenuItem>
                ))}
            </Select>
        </InputContainer>
    );
}

export default SingleSelect;
