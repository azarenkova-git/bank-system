import Tag from "./Tag/Tag";
import styles from "./Tags.module.css";

interface ITagsProps {
    tags: string[];
}

function Tags({ tags }: ITagsProps) {
    return (
        <div className={styles.container}>
            {tags.map((tag) => (
                <Tag tag={tag} key={tag} />
            ))}
        </div>
    );
}

export default Tags;
