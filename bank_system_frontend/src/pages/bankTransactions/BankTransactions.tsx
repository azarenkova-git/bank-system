import { BankTransaction } from "../../backendTypes/entities/BankTransaction";
import List from "../../components/List/List";
import { IListRendererProps } from "../../components/List/ListItem/ListItem";
import { IListToolbarItem } from "../../components/List/ListToolbar/ListToolbar";
import Modal from "../../components/Modal/Modal";
import Tag from "../../components/Tags/Tag/Tag";
import { useGetQuery } from "../../utils/useGetQuery";
import useModalState from "../../utils/useModalState";
import CreateDepositTransactionForm from "./CreateDepositTransactionForm/CreateDepositTransactionForm";
import CreateTransferTransactionForm from "./CreateTransferTransactionForm/CreateTransferTransactionForm";
import CreateWithdrawTransactionForm from "./CreateWithdrawTransactionForm/CreateWithdrawTransactionForm";

function BankTransactionItem({ data }: IListRendererProps<BankTransaction>) {
    return (
        <div>
            <Tag tag={data.type} />
            <Tag tag={data.status} />
            <div>{data.amountInCents} cents</div>
        </div>
    );
}

function BankTransactions() {
    const [transactions] = useGetQuery<BankTransaction[]>("bank-transaction/find-all");

    const [isDepositTransactionFormOpen, openDepositTransactionForm, closeDepositTransactionForm] = useModalState();
    const [isWithdrawTransactionFormOpen, openWithdrawTransactionForm, closeWithdrawTransactionForm] = useModalState();
    const [isTransferTransactionFormOpen, openTransferTransactionForm, closeTransferTransactionForm] = useModalState();

    const toolbarItems: IListToolbarItem[] = [
        {
            text: "Пополнить счет",
            action: () => openDepositTransactionForm(),
        },
        {
            text: "Снять со счета",
            action: () => openWithdrawTransactionForm(),
        },
        {
            text: "Перевести",
            action: () => openTransferTransactionForm(),
        },
    ];

    return (
        <>
            <List items={transactions} Renderer={BankTransactionItem} toolbarItems={toolbarItems} />

            <Modal isOpen={isDepositTransactionFormOpen} close={closeDepositTransactionForm} title="Пополнить счет">
                <CreateDepositTransactionForm />
            </Modal>

            <Modal isOpen={isWithdrawTransactionFormOpen} close={closeWithdrawTransactionForm} title="Снять со счета">
                <CreateWithdrawTransactionForm />
            </Modal>

            <Modal isOpen={isTransferTransactionFormOpen} close={closeTransferTransactionForm} title="Перевести">
                <CreateTransferTransactionForm />
            </Modal>
        </>
    );
}

export default BankTransactions;
