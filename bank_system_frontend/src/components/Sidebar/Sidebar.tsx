import styles from "./Sidebar.module.css";
import SidebarItem, { ISidebarItem } from "./SidebarItem/SidebarItem";

function Sidebar() {
    const items: ISidebarItem[] = [
        {
            path: "",
            text: "Главная страница",
        },
        {
            path: "banks",
            text: "Банки",
        },
        {
            path: "bankClients",
            text: "Клиенты",
        },
        {
            path: "bankAccounts",
            text: "Счета",
        },
        {
            path: "transactions",
            text: "Транзакции",
        },
    ];

    return (
        <div className={styles.container}>
            {items.map((item) => (
                <SidebarItem item={item} key={item.path} />
            ))}
        </div>
    );
}

export default Sidebar;
