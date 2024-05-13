import { useMutation } from "@tanstack/react-query";
import axios, { AxiosError } from "axios";
import { toast } from "react-toastify";
import config from "../config/config";
import HttpMethod from "./httpMethod";
import queryClient from "./queryClient";

interface IErrorMessage {
    message: string;
}

export default function useMutationQuery<D, E = unknown>(path: string, method: HttpMethod, onSuccess?: Function) {
    const mutation = useMutation(
        async (data: D) => {
            console.log(data);
            const result = await axios({
                method,
                data,
                url: `${config.backendUrl}/${path}`,
            });

            return result.data as E;
        },
        {
            onSuccess: async () => {
                await queryClient.invalidateQueries();
                onSuccess?.();
            },
            onError: async (error: AxiosError<IErrorMessage>) => {
                const message = error.response?.data?.message;

                if (!message) return;

                toast(message, {
                    type: "error",
                    position: "top-right",
                    autoClose: 5000,
                    closeOnClick: true,
                    pauseOnHover: true,
                });
            },
        }
    );

    const mutate = mutation.mutate as (data: D) => Promise<E>;

    return [mutate, mutation.status === "loading"] as const;
}
