package com.mendes.repository;

import com.mendes.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author mendes
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT account FROM Account account WHERE account.accountNumber=:accountNumber ")
    Account findByAccountNumber(Integer accountNumber);
}
