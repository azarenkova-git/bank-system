import { FC } from "react";
import { AbstractEntity } from "../../../backendTypes/entities/AbstractEntity";
import styles from "./ListItem.module.css";

export interface IListRendererProps<E extends AbstractEntity = AbstractEntity> {
    data: E;
}

export type ListItemRenderer = FC<IListRendererProps>;

interface IListItemProps {
    item: AbstractEntity;
    Renderer: ListItemRenderer;
}

function ListItem({ item, Renderer }: IListItemProps) {
    const content = <Renderer data={item} />;
    return <div className={styles.container}>{content}</div>;
}

export default ListItem;
