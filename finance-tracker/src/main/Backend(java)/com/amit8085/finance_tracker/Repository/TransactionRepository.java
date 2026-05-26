package com.amit8085.finance_tracker.Repository;

import com.amit8085.finance_tracker.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUserId(Long userId);
}
