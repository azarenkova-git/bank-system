import com.example.bank.BankApplication;
import com.example.bank.app.dto.BankDto;
import com.example.bank.app.entities.Bank;
import com.example.bank.app.services.BankService;
import com.example.bank.utils.enums.BankAccountType;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BankApplication.class)
@ActiveProfiles("test")
public class BankUnitTest {
    @Autowired
    private BankService bankService;

    @Test
    public void test() {
        Bank bank = createBankSuccessfully();
        updateBankSuccessfully(bank.getId());
        findBankSuccessfully(bank, bank.getId());
    }

    private Bank createBankSuccessfully() {
        BankDto bankDto = new BankDto();

        bankDto.setBankName("Bank of America");
        bankDto.setOperationLimit(1000);
        bankDto.setAllowedBankAccountTypes(Set.of(BankAccountType.DEBIT));

        Bank bank = bankService.create(bankDto);

        Assertions.assertEquals(bank.getBankName(), bankDto.getBankName());
        Assertions.assertEquals(bank.getOperationLimitWithQuestionableClients(), bankDto.getOperationLimit());
        Assertions.assertEquals(bank.getAllowedBankAccountTypes(), bankDto.getAllowedBankAccountTypes());

        return bankService.create(bankDto);
    }

    private void updateBankSuccessfully(String id) {
        BankDto bankDto = new BankDto();

        bankDto.setBankName("Bank of Russia");
        bankDto.setOperationLimit(2000);
        bankDto.setAllowedBankAccountTypes(Set.of(BankAccountType.CREDIT));

        Bank bank = bankService.update(id, bankDto);

        Assertions.assertEquals(bank.getBankName(), bankDto.getBankName());
        Assertions.assertEquals(bank.getOperationLimitWithQuestionableClients(), bankDto.getOperationLimit());
        Assertions.assertEquals(bank.getAllowedBankAccountTypes(), bankDto.getAllowedBankAccountTypes());
    }

    private void findBankSuccessfully(Bank bank, String id) {
        Bank foundBank = bankService.find(id);

        Assertions.assertEquals(foundBank.getBankName(), bank.getBankName());
        Assertions.assertEquals(
            foundBank.getOperationLimitWithQuestionableClients(),
            bank.getOperationLimitWithQuestionableClients()
        );
        Assertions.assertEquals(foundBank.getAllowedBankAccountTypes(), bank.getAllowedBankAccountTypes());
    }
}
