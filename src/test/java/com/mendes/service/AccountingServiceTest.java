package com.mendes.service;

import com.mendes.enums.Currency;
import com.mendes.model.Account;
import com.mendes.model.Accounting;
import com.mendes.model.ResultModel;
import com.mendes.repository.AccountRepository;
import com.mendes.repository.AccountingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mendes
 */

@SpringBootTest
class AccountingServiceTest {

    private static final Integer DEFAULT_SENDER_ACCOUNT_NUMBER = 33;
    private static final Integer DEFAULT_RECEIVER_ACCOUNT_NUMBER = 44;
    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(10.99);

    private static final Integer DEFAULT_ACCOUNT_NUMBER = 11;
    private static final Currency DEFAULT_CURRENCY_CODE = Currency.USD;
    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(11.99);

    private static final Integer FIRST_ACCOUNT_NUMBER = 22;
    private static final Currency FIRST_CURRENCY_CODE = Currency.USD;
    private static final BigDecimal FIRST_BALANCE = new BigDecimal(12.99);

    Accounting defaultAccounting;
    ResultModel resultModel;

    Account defaultAccount;
    Account firstAccount;

    @Autowired
    private AccountingService accountingService;

    @Autowired
    private AccountingRepository accountingRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {

        defaultAccount = new Account();
        defaultAccount.setAccountNumber(DEFAULT_ACCOUNT_NUMBER);
        defaultAccount.setCurrencyCode(DEFAULT_CURRENCY_CODE);
        defaultAccount.setBalance(DEFAULT_BALANCE);

        firstAccount = new Account();
        firstAccount.setAccountNumber(FIRST_ACCOUNT_NUMBER);
        firstAccount.setCurrencyCode(FIRST_CURRENCY_CODE);
        firstAccount.setBalance(FIRST_BALANCE);

        defaultAccounting = new Accounting();
        defaultAccounting.setSenderAccountNumber(DEFAULT_ACCOUNT_NUMBER);
        defaultAccounting.setReceiverAccountNumber(FIRST_ACCOUNT_NUMBER);
        defaultAccounting.setAmount(DEFAULT_AMOUNT);
    }

    @AfterEach
    void clear() {

        if (defaultAccounting != null && defaultAccounting.getId() != null) {
            accountingRepository.deleteById(defaultAccounting.getId());
        }
        if (defaultAccount != null && defaultAccount.getId() != null) {
            accountRepository.deleteById(defaultAccount.getId());
        }
        if (firstAccount != null && firstAccount.getId() != null) {
            accountRepository.deleteById(firstAccount.getId());
        }
    }

    @Test
    void save() {

        accountService.save(defaultAccount);
        accountService.save(firstAccount);
        resultModel = accountingService.save(defaultAccounting);
        assertFalse(resultModel.isError());
    }

    @Test
    void accountSenderHas() {

        accountService.save(defaultAccount);
        accountService.save(firstAccount);
        defaultAccounting.setSenderAccountNumber(DEFAULT_SENDER_ACCOUNT_NUMBER);
        resultModel = accountingService.save(defaultAccounting);
        assertTrue(resultModel.isError());
    }

    @Test
    void accountReceiverHas() {

        accountService.save(defaultAccount);
        accountService.save(firstAccount);
        defaultAccounting.setReceiverAccountNumber(DEFAULT_RECEIVER_ACCOUNT_NUMBER);
        resultModel = accountingService.save(defaultAccounting);
        assertTrue(resultModel.isError());
    }

    @Test
    void currencyCodeIsEqual() {

        defaultAccount.setCurrencyCode(Currency.EUR);
        accountService.save(defaultAccount);
        accountService.save(firstAccount);
        resultModel = accountingService.save(defaultAccounting);
        assertTrue(resultModel.isError());
    }

    @Test
    void amount() {

        accountService.save(defaultAccount);
        accountService.save(firstAccount);
        defaultAccounting.setAmount(new BigDecimal(100));
        resultModel = accountingService.save(defaultAccounting);
        assertTrue(resultModel.isError());

    }

    @Test
    void list() {

        accountService.save(defaultAccount);
        accountService.save(firstAccount);
        resultModel = accountingService.save(defaultAccounting);
        assertFalse(resultModel.isError());

        List<Accounting> accountings = accountingService.list();
        assertTrue(accountings.size() > 0);
    }
}