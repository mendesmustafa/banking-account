package com.mendes.service;

import com.mendes.model.Account;
import com.mendes.model.Accounting;
import com.mendes.model.ResultModel;
import com.mendes.repository.AccountingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author mendes
 */

@Service
public class AccountingService {

    private final AccountingRepository accountingRepository;
    private final AccountService accountService;

    public AccountingService(AccountingRepository accountingRepository, AccountService accountService) {
        this.accountingRepository = accountingRepository;
        this.accountService = accountService;
    }

    public ResultModel save(Accounting model) {
        String referenceNumber = UUID.randomUUID().toString();
        Account accountSender = accountService.findByAccountNumber(model.getSenderAccountNumber());
        Account accountReceiver = accountService.findByAccountNumber(model.getReceiverAccountNumber());
        if (accountSender == null || accountReceiver == null ||
                accountSender.getCurrencyCode() != accountReceiver.getCurrencyCode() ||
                accountSender.getBalance().compareTo(model.getAmount()) < 0) {
            return new ResultModel(true, referenceNumber);
        }
        accountingRepository.save(model);
        return new ResultModel(false, referenceNumber);
    }

    public List<Accounting> list() {
        return accountingRepository.findAll();
    }
}
