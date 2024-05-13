import { Button } from "@mui/material";
import { Form, useFormikContext } from "formik";
import { ReactNode } from "react";
import styles from "./FormLayout.module.css";

interface IFormLayoutProps {
    children: ReactNode;
    isDataLoading: boolean;
    onCancel?: Function;
    onSave?: Function;
}

function FormLayout({ children, isDataLoading, onCancel, onSave }: IFormLayoutProps) {
    const formik = useFormikContext();
    const isSavingDisabled = isDataLoading || !formik.isValid;

    const save = () => {
        formik.submitForm();
        onSave?.();
    };

    const cancel = () => {
        onCancel?.();
    };

    return (
        <div className={styles.container}>
            <div className={styles.content}>
                <Form style={{ display: "contents" }}>{children}</Form>
            </div>
            <div className={styles.footer}>
                <Button variant="contained" color="primary" disabled={isSavingDisabled} onClick={() => save()}>
                    Сохранить
                </Button>
                <Button variant="contained" color="secondary" onClick={() => cancel()}>
                    Отменить
                </Button>
            </div>
        </div>
    );
}

export default FormLayout;
