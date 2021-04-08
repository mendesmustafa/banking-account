package com.mendes.service;

import com.mendes.model.Account;
import com.mendes.model.ResultModel;
import com.mendes.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author mendes
 */

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResultModel save(Account model) {

        String referenceNumber = UUID.randomUUID().toString();

        try {
            accountRepository.save(model);
            return new ResultModel(false, referenceNumber);

        } catch (Exception e) {
            return new ResultModel(true, referenceNumber);
        }
    }

    public List<Account> list() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    public Account findByAccountNumber(Integer accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
