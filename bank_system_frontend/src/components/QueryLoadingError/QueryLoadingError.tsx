interface ILoadingErrorProps {
    error: any;
}

function QueryLoadingError({ error }: ILoadingErrorProps) {
    return <div>Ошибка загрузки: {error?.toString()}</div>;
}

export default QueryLoadingError;
