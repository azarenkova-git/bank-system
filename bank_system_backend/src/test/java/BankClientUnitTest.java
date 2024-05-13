import com.example.bank.BankApplication;
import com.example.bank.app.dto.BankClientDto;
import com.example.bank.app.entities.BankClient;
import com.example.bank.app.services.BankClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BankApplication.class)
@ActiveProfiles("test")
public class BankClientUnitTest {
    @Autowired
    private BankClientService bankClientService;

    @Test
    public void test() {
        BankClient bankClient = createBankClientSuccessfully();
        updateBankClientSuccessfully(bankClient.getId());
        findBankClientSuccessfully(bankClient, bankClient.getId());
    }

    private BankClient createBankClientSuccessfully() {
        BankClientDto bankClientDto = new BankClientDto();

        bankClientDto.setFirstName("John");
        bankClientDto.setLastName("Doe");

        BankClient bankClient = bankClientService.create(bankClientDto);

        Assertions.assertEquals(bankClient.getFirstName(), bankClientDto.getFirstName());
        Assertions.assertEquals(bankClient.getLastName(), bankClientDto.getLastName());
        Assertions.assertTrue(bankClient.getHasLimitedOperations());

        return bankClient;
    }

    private void updateBankClientSuccessfully(String id) {
        BankClientDto bankClientDto = new BankClientDto();

        bankClientDto.setFirstName("Jane");
        bankClientDto.setLastName("Doe");
        bankClientDto.setAddress("123 Main St.");
        bankClientDto.setPassportId(1234567890L);

        BankClient bankClient = bankClientService.update(id, bankClientDto);

        Assertions.assertEquals(bankClient.getFirstName(), bankClientDto.getFirstName());
        Assertions.assertEquals(bankClient.getLastName(), bankClientDto.getLastName());
        Assertions.assertEquals(bankClient.getAddress(), bankClientDto.getAddress());
        Assertions.assertEquals(bankClient.getPassportId(), bankClientDto.getPassportId());
        Assertions.assertFalse(bankClient.getHasLimitedOperations());
    }

    private void findBankClientSuccessfully(BankClient bankClient, String id) {
        BankClient bankClientFound = bankClientService.find(id);

        Assertions.assertEquals(bankClientFound.getFirstName(), bankClient.getFirstName());
        Assertions.assertEquals(bankClientFound.getLastName(), bankClient.getLastName());
        Assertions.assertEquals(bankClientFound.getAddress(), bankClient.getAddress());
        Assertions.assertEquals(bankClientFound.getPassportId(), bankClient.getPassportId());
        Assertions.assertFalse(bankClientFound.getHasLimitedOperations());
    }
}
