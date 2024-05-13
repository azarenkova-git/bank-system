export default function enumToArray<V extends object>(em: V) {
    return Object.values(em) as (keyof V)[];
}
