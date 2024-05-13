import { Button } from "@mui/material";
import styles from "./ListToolbar.module.css";

export interface IListToolbarItem {
    text: string;
    action: Function;
}

interface IListToolbarProps {
    items: IListToolbarItem[];
}

function ListToolbar({ items }: IListToolbarProps) {
    return (
        <div className={styles.container}>
            {items.map((item) => {
                const onClick = () => item.action();
                return (
                    <Button onClick={onClick} key={item.text}>
                        {item.text}
                    </Button>
                );
            })}
        </div>
    );
}

export default ListToolbar;
