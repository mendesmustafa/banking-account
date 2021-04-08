package com.mendes.model;

import com.mendes.enums.Currency;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mendes
 */

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ACCOUNT_ID_SEQ")
    @SequenceGenerator(name = "ACCOUNT_ID_SEQ", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ACCOUNT_NUMBER", unique = true)
    private Integer accountNumber;

    @Column(name = "CURRENY_CODE")
    @Enumerated(EnumType.STRING)
    private Currency currencyCode = Currency.USD;

    @Column(name = "BALANCE", precision = 10, scale = 2, nullable = false)
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Currency getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Currency currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}