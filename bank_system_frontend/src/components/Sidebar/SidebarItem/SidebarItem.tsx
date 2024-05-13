import clsx from "clsx";
import { useLocation, useNavigate } from "react-router-dom";
import styles from "./SidebarItem.module.css";

export interface ISidebarItem {
    path: string;
    text: string;
}

interface ISidebarItemProps {
    item: ISidebarItem;
}

function SidebarItem({ item }: ISidebarItemProps) {
    const navigate = useNavigate();

    const location = useLocation();
    const isIndex = item.path === "";
    const isSelected = (isIndex && location.pathname === "/app") || location.pathname === `/app/${item.path}`;

    const onClick = () => {
        if (isSelected) return;
        navigate(`/app/${item.path}`);
    };

    return (
        <div className={clsx(styles.container, isSelected && styles.selected)} onClick={onClick}>
            {item.text}
        </div>
    );
}

export default SidebarItem;
