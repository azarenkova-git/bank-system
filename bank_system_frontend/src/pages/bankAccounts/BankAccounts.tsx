import { AbstractBankAccount } from "../../backendTypes/entities/AbstractBankAccount";
import List from "../../components/List/List";
import { IListRendererProps } from "../../components/List/ListItem/ListItem";
import { IListToolbarItem } from "../../components/List/ListToolbar/ListToolbar";
import Modal from "../../components/Modal/Modal";
import Tag from "../../components/Tags/Tag/Tag";
import { useGetQuery } from "../../utils/useGetQuery";
import useModalState from "../../utils/useModalState";
import CreateCreditBankAccountForm from "./CreateCreditBankAccountForm/CreateCreditBankAccountForm";
import CreateDebitBankAccountForm from "./CreateDebitBankAccountForm/CreateDebitBankAccountForm";
import CreateDepositBankAccountForm from "./CreateDepositBankAccountForm/CreateDepositBankAccountForm";

function BankAccountItem({ data }: IListRendererProps<AbstractBankAccount>) {
    return (
        <div>
            <Tag tag={data.bankAccountType} />
            <div>{data.bank.bankName}</div>
            <div>
                {data.bankClient.firstName} {data.bankClient.lastName}
            </div>
            <div>{data.balanceInCents}</div>
        </div>
    );
}

function BankAccounts() {
    const [bankAccounts] = useGetQuery<AbstractBankAccount[]>("bank-account/find-all");

    const [isCreateCreditBankAccountFormOpen, openCreditBankAccountForm, closeCreditBankAccountForm] = useModalState();
    const [isCreateDebitBankAccountFormOpen, openDebitBankAccountForm, closeDebitBankAccountForm] = useModalState();
    const [isDepositDebitBankAccountFormOpen, openDepositBankAccountForm, closeDepositBankAccountForm] =
        useModalState();

    const toolbarItems: IListToolbarItem[] = [
        {
            text: "Создать кредитный счет",
            action: () => openCreditBankAccountForm(),
        },
        {
            text: "Создать дебетовый счет",
            action: () => openDebitBankAccountForm(),
        },
        {
            text: "Создать депозитный счет",
            action: () => openDepositBankAccountForm(),
        },
    ];

    return (
        <>
            <List items={bankAccounts} Renderer={BankAccountItem} toolbarItems={toolbarItems} />
            <Modal
                isOpen={isCreateCreditBankAccountFormOpen}
                close={closeCreditBankAccountForm}
                title="Создать кредитный счет"
            >
                <CreateCreditBankAccountForm />
            </Modal>

            <Modal
                isOpen={isCreateDebitBankAccountFormOpen}
                close={closeDebitBankAccountForm}
                title="Создать дебетовый счет"
            >
                <CreateDebitBankAccountForm />
            </Modal>

            <Modal
                isOpen={isDepositDebitBankAccountFormOpen}
                close={closeDepositBankAccountForm}
                title="Создать депозитный счет"
            >
                <CreateDepositBankAccountForm />
            </Modal>
        </>
    );
}

export default BankAccounts;
