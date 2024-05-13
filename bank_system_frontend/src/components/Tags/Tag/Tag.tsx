import styles from "./Tag.module.css";

interface ITagProps {
    tag: string;
}

function Tag({ tag }: ITagProps) {
    return <div className={styles.container}>{tag}</div>;
}

export default Tag;
