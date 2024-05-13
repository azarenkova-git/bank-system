import { useState } from "react";

export default function useModalState() {
    const [isOpen, setIsOpen] = useState(false);

    const open = () => setIsOpen(true);
    const close = () => setIsOpen(false);

    return [isOpen, open, close] as const;
}
