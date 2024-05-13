import com.example.bank.BankApplication;
import com.example.bank.app.dto.BankClientDto;
import com.example.bank.app.dto.BankDto;
import com.example.bank.app.dto.CreditBankAccountDto;
import com.example.bank.app.dto.DebitBankAccountDto;
import com.example.bank.app.dto.DepositDto;
import com.example.bank.app.dto.TransferDto;
import com.example.bank.app.dto.WithdrawDto;
import com.example.bank.app.entities.AbstractBankAccount;
import com.example.bank.app.entities.Bank;
import com.example.bank.app.entities.BankClient;
import com.example.bank.app.entities.BankTransaction;
import com.example.bank.app.entities.CreditBankAccount;
import com.example.bank.app.entities.DebitBankAccount;
import com.example.bank.app.services.BankClientService;
import com.example.bank.app.services.BankService;
import com.example.bank.app.services.BankTransactionService;
import com.example.bank.app.services.CreditBankAccountService;
import com.example.bank.app.services.DebitBankAccountService;
import com.example.bank.utils.enums.BankAccountType;
import com.example.bank.utils.enums.BankTransactionStatus;
import com.example.bank.utils.exceptions.ForbiddenAccountTypeException;
import com.example.bank.utils.exceptions.OperationIsNotAllowedException;
import com.example.bank.utils.xml.XmlPersistenceService;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BankApplication.class)
@ActiveProfiles("test")
public class MainIntegrationTest {
    private final BankService bankService;
    private final BankClientService bankClientService;
    private final CreditBankAccountService creditBankAccountService;
    private final DebitBankAccountService debitBankAccountService;
    private final BankTransactionService bankTransactionService;
    private final XmlPersistenceService xmlPersistenceService;

    @Autowired
    public MainIntegrationTest(
        BankService bankService,
        BankClientService bankClientService,
        CreditBankAccountService creditBankAccountService,
        DebitBankAccountService debitBankAccountService,
        BankTransactionService bankTransactionService,
        XmlPersistenceService xmlPersistenceService
    ) {
        this.bankService = bankService;
        this.bankClientService = bankClientService;
        this.creditBankAccountService = creditBankAccountService;
        this.debitBankAccountService = debitBankAccountService;
        this.bankTransactionService = bankTransactionService;
        this.xmlPersistenceService = xmlPersistenceService;
    }

    @Test
    public void test() {
        Bank creditBank = createBank(BankAccountType.CREDIT);
        Bank depositBank = createBank(BankAccountType.DEPOSIT);

        BankClient firstClient = createBankClient(false);
        BankClient secondClient = createBankClient(true);

        CreditBankAccount firstClientCreditAccount = createCreditBankAccount(creditBank, firstClient);

        // Нельзя создать счет с типом, который не поддерживается банком
        Assertions.assertThrows(
            ForbiddenAccountTypeException.class,
            () -> createCreditBankAccount(depositBank, secondClient)
        );

        DebitBankAccount secondClientDepositAccount = createDepositBankAccount(depositBank, secondClient);

        // Нельзя уходить в минус
        Assertions.assertThrows(
            OperationIsNotAllowedException.class,
            () -> createWithdrawalTransaction(secondClientDepositAccount, 100)
        );

        createDepositTransaction(firstClientCreditAccount, 100);

        // Пополнение работает корректно
        Assertions.assertEquals(firstClientCreditAccount.getBalanceInCents(), 100);

        createWithdrawalTransaction(firstClientCreditAccount, 200);
        createWithdrawalTransaction(firstClientCreditAccount, 200);

        // Комиссия применяется
        Assertions.assertEquals(firstClientCreditAccount.getBalanceInCents(), -320);

        // Нельзя превысить кредитный лимит
        Assertions.assertThrows(
            OperationIsNotAllowedException.class,
            () -> createWithdrawalTransaction(firstClientCreditAccount, 500)
        );

        createDepositTransaction(firstClientCreditAccount, 1100);

        // Нельзя превысить лимит для клиентов без полной информации
        Assertions.assertThrows(
            OperationIsNotAllowedException.class,
            () -> createDepositTransaction(firstClientCreditAccount, 2000)
        );

        // Проверяем корректность балансов для дальнейшего тестирования
        Assertions.assertEquals(firstClientCreditAccount.getBalanceInCents(), 670);
        Assertions.assertEquals(secondClientDepositAccount.getBalanceInCents(), 0);

        BankTransaction transferTransaction = createTransferTransaction(
            firstClientCreditAccount,
            secondClientDepositAccount,
            100
        );

        // Перевод работает корректно
        Assertions.assertEquals(firstClientCreditAccount.getBalanceInCents(), 570);
        Assertions.assertEquals(secondClientDepositAccount.getBalanceInCents(), 100);

        rollbackTransaction(transferTransaction.getId());

        // Транзакция откатывается
        Assertions.assertEquals(firstClientCreditAccount.getBalanceInCents(), 670);
        Assertions.assertEquals(secondClientDepositAccount.getBalanceInCents(), 0);
        Assertions.assertEquals(transferTransaction.getStatus(), BankTransactionStatus.REVERTED);

        xmlPersistenceService.clear();

        BankTransaction revertedTransaction = bankTransactionService.find(transferTransaction.getId());

        // Проверяем, что данные сохраняются в XML. Заодно, тестируем equals и hashCode
        Assertions.assertEquals(
            revertedTransaction.getBankAccountReceiver(),
            transferTransaction.getBankAccountReceiver()
        );

        Assertions.assertEquals(
            revertedTransaction.getBankAccountSender(),
            transferTransaction.getBankAccountSender()
        );

        Set<Bank> oldSet = Set.of(depositBank, creditBank);

        Set<Bank> newSet = new HashSet<>(bankService.findAll());

        // Проверяем, что все findAll работает корректно
        Assertions.assertEquals(oldSet, newSet);
    }

    private Bank createBank(BankAccountType bankAccountType) {
        BankDto bankDto = new BankDto();

        bankDto.setBankName("Bank of America");
        bankDto.setOperationLimit(1500);
        bankDto.setAllowedBankAccountTypes(Set.of(BankAccountType.DEBIT, bankAccountType));

        return bankService.create(bankDto);
    }

    private BankClient createBankClient(boolean fullInfo) {
        BankClientDto bankClientDto = new BankClientDto();

        bankClientDto.setFirstName("John");
        bankClientDto.setLastName("Doe");

        if (fullInfo) {
            bankClientDto.setAddress("123 Main St.");
            bankClientDto.setPassportId(1234567890L);
        }

        return bankClientService.create(bankClientDto);
    }

    private CreditBankAccount createCreditBankAccount(Bank bank, BankClient bankClient) {
        CreditBankAccountDto creditBankAccountDto = new CreditBankAccountDto();

        creditBankAccountDto.setBankId(bank.getId());
        creditBankAccountDto.setBankClientId(bankClient.getId());
        creditBankAccountDto.setCommissionRate(100);
        creditBankAccountDto.setCreditLimitInCents(500);

        return creditBankAccountService.create(creditBankAccountDto);
    }

    private DebitBankAccount createDepositBankAccount(Bank bank, BankClient bankClient) {
        DebitBankAccountDto debitBankAccountDto = new DebitBankAccountDto();

        debitBankAccountDto.setBankId(bank.getId());
        debitBankAccountDto.setBankClientId(bankClient.getId());

        return debitBankAccountService.create(debitBankAccountDto);
    }

    private BankTransaction createWithdrawalTransaction(AbstractBankAccount bankAccount, long amountInCents) {
        WithdrawDto withdrawDto = new WithdrawDto();

        withdrawDto.setBankAccountId(bankAccount.getId());
        withdrawDto.setAmountInCents(amountInCents);

        return bankTransactionService.withdraw(withdrawDto);
    }

    private BankTransaction createDepositTransaction(AbstractBankAccount bankAccount, long amountInCents) {
        DepositDto depositDto = new DepositDto();

        depositDto.setBankAccountId(bankAccount.getId());
        depositDto.setAmountInCents(amountInCents);

        return bankTransactionService.deposit(depositDto);
    }

    private BankTransaction createTransferTransaction(
        AbstractBankAccount senderBankAccount,
        AbstractBankAccount receiverBankAccount,
        long amountInCents
    ) {
        TransferDto transferDto = new TransferDto();

        transferDto.setSenderBankAccountId(senderBankAccount.getId());
        transferDto.setReceiverBankAccountId(receiverBankAccount.getId());
        transferDto.setAmountInCents(amountInCents);

        return bankTransactionService.transfer(transferDto);
    }

    private void rollbackTransaction(String id) {
        bankTransactionService.reverse(id);
    }
}
