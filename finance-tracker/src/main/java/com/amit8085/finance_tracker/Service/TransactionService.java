package com.amit8085.finance_tracker.Service;

import com.amit8085.finance_tracker.Repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository trepo;

    public TransactionService(TransactionRepository trepo){
        this.trepo =trepo;
    }


}
