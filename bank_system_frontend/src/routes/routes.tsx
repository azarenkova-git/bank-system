import { createBrowserRouter, Navigate } from "react-router-dom";
import Layout from "../components/Layout/Layout";
import BankAccounts from "../pages/bankAccounts/BankAccounts";
import BankClients from "../pages/bankClients/BankClients";
import Banks from "../pages/banks/Banks";
import BankTransactions from "../pages/bankTransactions/BankTransactions";
import Main from "../pages/main/Main";

const routes = createBrowserRouter([
    {
        path: "app",
        element: <Layout />,
        children: [
            {
                index: true,
                element: <Main />,
            },
            {
                path: "banks",
                element: <Banks />,
            },
            {
                path: "bankClients",
                element: <BankClients />,
            },
            {
                path: "bankAccounts",
                element: <BankAccounts />,
            },
            {
                path: "transactions",
                element: <BankTransactions />,
            },
            {
                path: "*",
                element: <Navigate to="/app" />,
            },
        ],
    },
    {
        path: "*",
        element: <Navigate to="/app" />,
    },
]);

export default routes;
