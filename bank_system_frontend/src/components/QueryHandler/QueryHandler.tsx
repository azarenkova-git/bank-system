import { IGetQueryInfo } from "../../utils/useGetQuery";
import QueryLoading from "../QueryLoading/QueryLoading";
import QueryLoadingError from "../QueryLoadingError/QueryLoadingError";

interface IQueryHandlerProps {
    queryState: IGetQueryInfo;
}

function QueryHandler({ queryState }: IQueryHandlerProps) {
    if (queryState.isLoading) {
        return <QueryLoading />;
    }

    if (queryState.error) {
        return <QueryLoadingError error={queryState.error} />;
    }
}

export default QueryHandler;
