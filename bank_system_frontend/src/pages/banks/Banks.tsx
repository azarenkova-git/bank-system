import { Bank } from "../../backendTypes/entities/Bank";
import List from "../../components/List/List";
import { IListRendererProps } from "../../components/List/ListItem/ListItem";
import { IListToolbarItem } from "../../components/List/ListToolbar/ListToolbar";
import Modal from "../../components/Modal/Modal";
import Tags from "../../components/Tags/Tags";
import { useGetQuery } from "../../utils/useGetQuery";
import useModalState from "../../utils/useModalState";
import CreateBankForm from "./CreateBankForm/CreateBankForm";

function BankItem({ data }: IListRendererProps<Bank>) {
    return (
        <div>
            <div>{data.bankName}</div>
            <div>{data.operationLimitWithQuestionableClients} cents</div>
            <Tags tags={data.allowedBankAccountTypes} />
        </div>
    );
}

function Banks() {
    const [banks] = useGetQuery<Bank[]>("bank/find-all");

    const [isOpen, open, close] = useModalState();

    const toolbarItems: IListToolbarItem[] = [
        {
            text: "Создать банк",
            action: () => open(),
        },
    ];

    return (
        <>
            <List items={banks} Renderer={BankItem} toolbarItems={toolbarItems} />
            <Modal isOpen={isOpen} close={close} title="Создать банк">
                <CreateBankForm />
            </Modal>
        </>
    );
}

export default Banks;
