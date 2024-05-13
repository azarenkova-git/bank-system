import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import config from "../config/config";
import HttpMethod from "./httpMethod";

export interface IGetQueryInfo {
    noData: boolean;
    isLoading: boolean;
    error: any;
}

export function useGetQuery<D>(path: string) {
    const { data, isLoading, error } = useQuery({
        queryKey: [path],
        queryFn: async () => {
            const response = await axios({
                method: HttpMethod.GET,
                url: `${config.backendUrl}/${path}`,
            });
            return response.data as D;
        },
    });

    return [
        data,
        {
            error,
            isLoading,
            noData: Boolean(isLoading || error),
        } satisfies IGetQueryInfo,
    ] as const;
}
