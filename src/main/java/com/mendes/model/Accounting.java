package com.mendes.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mendes
 */

@Entity
@Table(name = "ACCOUNTING")
public class Accounting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ACCOUNTING_ID_SEQ")
    @SequenceGenerator(name = "ACCOUNTING_ID_SEQ", sequenceName = "ACCOUNTING_ID_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SENDER_ACCOUNT_NUMBER")
    private Integer senderAccountNumber;

    @Column(name = "RECEIVER_ACCOUNT_NUMBER")
    private Integer receiverAccountNumber;

    @Column(name = "AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(Integer senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public Integer getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(Integer receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
