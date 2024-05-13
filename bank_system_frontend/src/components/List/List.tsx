import { AbstractEntity } from "../../backendTypes/entities/AbstractEntity";
import styles from "./List.module.css";
import ListItem, { ListItemRenderer } from "./ListItem/ListItem";
import ListToolbar, { IListToolbarItem } from "./ListToolbar/ListToolbar";

interface IListProps {
    items: AbstractEntity[];
    Renderer: ListItemRenderer;
    toolbarItems: IListToolbarItem[];
}

function List({ items, Renderer, toolbarItems }: IListProps) {
    return (
        <div className={styles.container}>
            <div className={styles.toolbar}>
                <ListToolbar items={toolbarItems} />
            </div>
            <div className={styles.content}>
                {!items && <div>Загрузка...</div>}
                {items && items.map((item) => <ListItem item={item} Renderer={Renderer} key={item.id} />)}
            </div>
        </div>
    );
}

export default List;
