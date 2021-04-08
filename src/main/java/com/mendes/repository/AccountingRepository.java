package com.mendes.repository;

import com.mendes.model.Accounting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mendes
 */

@Repository
public interface AccountingRepository extends JpaRepository<Accounting, Long> {
}
