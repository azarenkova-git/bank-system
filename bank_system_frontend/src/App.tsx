import { LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { QueryClientProvider } from "@tanstack/react-query";
import { RouterProvider } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import routes from "./routes/routes";
import "./styles/index.css";
import "./styles/theme.css";
import queryClient from "./utils/queryClient";

import "react-toastify/dist/ReactToastify.css";

function App() {
    return (
        <LocalizationProvider dateAdapter={AdapterDateFns}>
            <QueryClientProvider client={queryClient}>
                <RouterProvider router={routes} />
                <ToastContainer />
            </QueryClientProvider>
        </LocalizationProvider>
    );
}

export default App;
