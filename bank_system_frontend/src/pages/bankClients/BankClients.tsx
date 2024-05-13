import { BankClient } from "../../backendTypes/entities/BankClient";
import List from "../../components/List/List";
import { IListRendererProps } from "../../components/List/ListItem/ListItem";
import { IListToolbarItem } from "../../components/List/ListToolbar/ListToolbar";
import Modal from "../../components/Modal/Modal";
import { useGetQuery } from "../../utils/useGetQuery";
import useModalState from "../../utils/useModalState";
import CreateBankClientForm from "./CreateBankClientForm/CreateBankClientForm";

function BankClientItem({ data }: IListRendererProps<BankClient>) {
    return (
        <div>
            <div>
                {data.firstName} {data.lastName}
            </div>
            <div>{data.address}</div>
            <div>{data.passportId}</div>
        </div>
    );
}

function BankClients() {
    const [bankClients] = useGetQuery<BankClient[]>("bank-client/find-all");

    const [isOpen, open, close] = useModalState();

    const toolbarItems: IListToolbarItem[] = [
        {
            text: "Создать банковского клиента",
            action: () => open(),
        },
    ];

    return (
        <>
            <List items={bankClients} Renderer={BankClientItem} toolbarItems={toolbarItems} />
            <Modal isOpen={isOpen} close={close} title="Создать банковского клиента">
                <CreateBankClientForm />
            </Modal>
        </>
    );
}

export default BankClients;
