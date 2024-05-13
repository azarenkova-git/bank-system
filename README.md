## Проект "Банковская система"

### Командна
1. Азаренкова Наталья Денисовна
2. Иванова Светлана Дмитриевна

### Описание запуска
Для сборки и запуска проекта целиком нужно запустить корневой [docker-compose.yml](docker-compose.yml) командой

```bash
docker-compose up
```

Backend запустится по адресу http://localhost:8080, frontend - http://localhost:8090.

Инструкции для "ручного" запуска - в файлах README.md в папках [bank_system_backend](bank_system_backend) 
и [bank_system_frontend](bank_system_frontend) соответственно.


### Описание функциональности
В рамках нашего проекта можно:

1. Создавать пользователей, указывая
    1. Имя
    2. Фамилию
    3. Паспортные данные
    4. Адрес
   
2. Создавать банки, указывая:
   1. Название банка
   2. Какие типы счетов можно в нем открыть (есть например банки, которые могут иметь только кредитные счета)
   3. Какой максимальный размер операций у клиентов, которые не указали все свои учетные данные 
(например указали имя фамилию, но не указали паспортные данные).  Они считаются “подозрительными” и операции
больше определенного размера блокируются
   
3. Создавать счета типа:
    1. Кредитные, указывая:
        1. Пользователя
        2. Банк
        3. Комиссию в процентах за каждую операцию при превышении кредитного лимита
        4. Кредитный лимит (в какой минус можно уйти)
    2. Дебетовые, указывая:
        1. Пользователя
        2. Банк
    3. Депозитные, указывая:
        1. Пользователя
        2. Банк
        3. Дату, когда счет “разблокируется”. До этого момента его можно только пополнять, 
но нельзя с него куда-то переводить деньги
       
4. Создавать транзакции типа
    1. Пополнение, указывая
        1. Сумму
        2. Счет, который пополняем
    2. Снятие, указывая
        1. Сумму
        2. Счет, с которого снимаем
    3. Перевод, указывая
        1. Сумму
        2. Счет, куда переводим
        3. Счет, откуда переводим
