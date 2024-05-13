import { FormControl, FormHelperText } from "@mui/material";
import { ReactNode } from "react";
import styles from "./InputContainer.module.css";

interface IInputContainerProps {
    children: ReactNode;
    error: string;
}

function InputContainer({ children, error }: IInputContainerProps) {
    return (
        <div className={styles.container}>
            <FormControl fullWidth>
                {children}
                <FormHelperText error>{error}</FormHelperText>
            </FormControl>
        </div>
    );
}

export default InputContainer;
