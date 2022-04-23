package com.mendes.service;

import com.mendes.enums.Currency;
import com.mendes.model.Account;
import com.mendes.model.ResultModel;
import com.mendes.repository.AccountRepository;
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
class AccountServiceTest {

    private static final Integer DEFAULT_ACCOUNT_NUMBER = 11;
    private static final Currency DEFAULT_CURRENCY_CODE = Currency.USD;
    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(99.99);

    Account defaultAccount;
    ResultModel resultModel;

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
    }

    @AfterEach
    void clear() {
        if (defaultAccount != null && defaultAccount.getId() != null) {
            accountRepository.deleteById(defaultAccount.getId());
        }
    }

    @Test
    void save() {
        resultModel = accountService.save(defaultAccount);
        assertFalse(resultModel.isError());
    }

    @Test
    void list() {
        resultModel = accountService.save(defaultAccount);
        assertFalse(resultModel.isError());
        List<Account> accounts = accountService.list();
        assertTrue(accounts.size() > 0);
    }

    @Test
    void findByAccountNumber() {
        resultModel = accountService.save(defaultAccount);
        assertFalse(resultModel.isError());
        Account accountNumber = accountService.findByAccountNumber(defaultAccount.getAccountNumber());
        assertEquals(DEFAULT_ACCOUNT_NUMBER, accountNumber.getAccountNumber());
    }
}