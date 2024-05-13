import { Modal as MUIModal } from "@mui/material";
import { ReactElement } from "react";
import { useEventListener } from "usehooks-ts";
import ModalContext from "./ModalContext";
import styles from "./Modal.module.css";

interface IModalProps {
    children: ReactElement;
    isOpen: boolean;
    close: Function;
    title: string;
}

function Modal({ title, children, close, isOpen }: IModalProps) {
    useEventListener("keydown", (e) => {
        if (e.key === "Escape") {
            close();
        }
    });

    return (
        <MUIModal open={isOpen} onClose={() => close()} disableAutoFocus>
            <div className={styles.parent}>
                <div
                    className={styles.container}
                    style={{
                        width: "345px",
                    }}
                >
                    <div className={styles.header}>
                        <div>{title}</div>
                    </div>
                    <div className={styles.content}>
                        <ModalContext.Provider value={close}>{children}</ModalContext.Provider>
                    </div>
                </div>
            </div>
        </MUIModal>
    );
}

export default Modal;
