package com.example.bank.utils.xml;

import com.example.bank.app.entities.AbstractBankAccount;
import com.example.bank.app.entities.AbstractEntity;
import com.example.bank.app.entities.Bank;
import com.example.bank.app.entities.BankClient;
import com.example.bank.app.entities.BankTransaction;
import com.example.bank.app.entities.CreditBankAccount;
import com.example.bank.app.entities.DebitBankAccount;
import com.example.bank.app.entities.DepositBankAccount;
import com.example.bank.utils.configuration.AppConfig;
import com.example.bank.utils.exceptions.EntityNotFoundException;
import com.example.bank.utils.exceptions.InternalException;
import com.example.bank.utils.others.SnakeCase;
import com.example.bank.utils.xml.adapters.AbstractEntityAdapter;
import com.example.bank.utils.xml.adapters.BankAccountAdapter;
import com.example.bank.utils.xml.adapters.BankAdapter;
import com.example.bank.utils.xml.adapters.BankClientAdapter;
import com.example.bank.utils.xml.adapters.BankTransactionAdapter;
import com.example.bank.utils.xml.adapters.CreditBankAccountAdapter;
import com.example.bank.utils.xml.adapters.DebitBankAccountAdapter;
import com.example.bank.utils.xml.adapters.DepositBankAccountAdapter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

@Service
public class XmlPersistenceService {
    private final Map<String, AbstractEntity> map = new HashMap<>();

    private final AbstractEntityAdapter[] adapters = new AbstractEntityAdapter[]{
        new BankAdapter(this),
        new BankClientAdapter(this),
        new BankTransactionAdapter(this),
        new BankAccountAdapter(this),
        new CreditBankAccountAdapter(this),
        new DebitBankAccountAdapter(this),
        new DepositBankAccountAdapter(this)
    };

    private final Class[] classes = new Class[]{
        Bank.class,
        BankClient.class,
        BankTransaction.class,
        BankClient.class,
        AbstractBankAccount.class,
        CreditBankAccount.class,
        DebitBankAccount.class,
        DepositBankAccount.class
    };

    private final JAXBContext jaxbContext = getJaxbContext();

    private final AppConfig appConfig;

    public XmlPersistenceService(AppConfig appConfig) {
        this.appConfig = appConfig;
        initXmlDirectories();
        loadAllEntitiesInMemory();
    }

    private JAXBContext getJaxbContext() {
        try {
            return JAXBContext.newInstance(classes);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private void marshal(String id, AbstractEntity entity, Class clazz) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        for (AbstractEntityAdapter adapter : adapters) {
            marshaller.setAdapter(adapter);
        }
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String filePath = getFilePath(id, clazz);
        File file = new File(filePath);
        marshaller.marshal(entity, file);
    }

    private AbstractEntity unmarshal(String id, Class clazz) throws JAXBException, FileNotFoundException {
        String filePath = getFilePath(id, clazz);
        FileReader fileReader = new FileReader(filePath);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        for (AbstractEntityAdapter adapter : adapters) {
            unmarshaller.setAdapter(adapter);
        }
        return (AbstractEntity) unmarshaller.unmarshal(fileReader);
    }

    /**
     * В начале работы программы создаем пустые директории для хранения xml файлов. Иначе программа упадет при
     * попытке сохранить что-то.
     */
    private void initXmlDirectories() {
        File rootDir = new File(appConfig.getXmlFilesLocation());

        if (appConfig.getClearXmlFilesLocation()) {
            FileSystemUtils.deleteRecursively(rootDir);
        }

        if (!rootDir.exists()) {
            boolean result = rootDir.mkdirs();
            if (!result) {
                throw new InternalException();
            }
        }

        for (Class clazz : classes) {
            String name = SnakeCase.getSnakeCaseClassName(clazz);
            File dir = new File(appConfig.getXmlFilesLocation() + "/" + name);
            if (!dir.exists()) {
                boolean result = dir.mkdirs();
                if (!result) {
                    throw new InternalException();
                }
            }
        }
    }

    /**
     * Сохраняем сущность в файловую систему. Если ее нет в кеше - обновляем кеш.
     */
    public <T extends AbstractEntity> void save(T entity, Class clazz) {
        try {
            String id = entity.getId();
            if (!map.containsKey(id)) {
                map.put(id, entity);
            }
            // Проходимся по цепочке наследования, и сохраняем сущность в папки родителей. Это нужно для того, чтобы
            // например при попытке получить какой-то абстрактный счет по id, мы могли получить его, не зная
            // наперед его конкретного типа.
            Class currentClass = clazz;
            Class stopClass = AbstractEntity.class;
            while (currentClass != null && currentClass != stopClass) {
                marshal(id, entity, currentClass);
                currentClass = currentClass.getSuperclass();
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получаем сущность по id. Если она есть в кеше - используем его, иначе загружаем из файловой системы.
     */
    public <T extends AbstractEntity> T load(String id, Class clazz) {
        if (!map.containsKey(id)) {
            try {
                map.put(id, unmarshal(id, clazz));
            } catch (FileNotFoundException e) {
                throw new EntityNotFoundException(id);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        return (T) map.get(id);
    }

    private void loadAllEntitiesInMemory() {
        for (Class clazz : classes) {
            String name = SnakeCase.getSnakeCaseClassName(clazz);
            File folder = new File(appConfig.getXmlFilesLocation() + "/" + name);

            for (File fileEntry : folder.listFiles()) {
                String fileName = fileEntry.getName();
                String fileNameWithoutExtension = fileName.replaceFirst("[.][^.]+$", "");
                load(fileNameWithoutExtension, clazz);
            }
        }
    }

    public <T extends AbstractEntity> List<T> findAll(Class<T> clazz) {
        List<T> list = new LinkedList<>();

        for (AbstractEntity value : map.values()) {
            if (clazz.isInstance(value)) {
                list.add((T) value);
            }
        }

        return list;
    }

    /**
     * Очищаем "кеш" - все сущности начнут загружаться из файлов заново при первых запросах. Используется только
     * в тестах. В продакшене не используется - вызовет страшные баги. И зачем оно вообще в продакшене?
     */
    public void clear() {
        map.clear();
    }

    /**
     * Получаем путь к файлу, в котором хранится сущность с указанным id. Путь состоит из корневой папки и названия
     * класса в формате snake_case.
     */
    private String getFilePath(String id, Class clazz) {
        String name = SnakeCase.getSnakeCaseClassName(clazz);
        return String.format("%s/%s/%s.xml", appConfig.getXmlFilesLocation(), name, id);
    }
}
