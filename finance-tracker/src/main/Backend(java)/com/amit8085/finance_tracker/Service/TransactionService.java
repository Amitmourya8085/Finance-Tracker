package com.amit8085.finance_tracker.Service;

import com.amit8085.finance_tracker.DTO.TransactionRequestDTO;
import com.amit8085.finance_tracker.DTO.TransactionResponseDTO;
import com.amit8085.finance_tracker.Entity.Transaction;
import com.amit8085.finance_tracker.Entity.TransactionType;
import com.amit8085.finance_tracker.Entity.User;
import com.amit8085.finance_tracker.Repository.TransactionRepository;
import com.amit8085.finance_tracker.Repository.UserRepository;
import org.apache.catalina.valves.rewrite.InternalRewriteMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository trepo;
    private final UserRepository urepo;

    public TransactionService(TransactionRepository trepo, UserRepository urepo){
        this.trepo =trepo;
        this.urepo = urepo;
    }
    //ADD TRANSACTION
    public TransactionResponseDTO addTransaction(TransactionRequestDTO dto){
       //validate Transaction Type;
        TransactionType type;
        try {
            type=TransactionType.valueOf(dto.getType().toUpperCase());
        }catch(Exception e){
            throw new RuntimeException("Invalid type. Use INCOME or EXPENSE");
        }
        //check for user;
        User user = urepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        //Create transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setType(type);
        transaction.setCategory(dto.getCategory());
        transaction.setDate(dto.getDate());
        transaction.setUser(user);

        Transaction saved = trepo.save(transaction);
        return new TransactionResponseDTO(
                saved.getAmount(),
                saved.getType(),
                saved.getCategory(),
                saved.getDate(),
                saved.getUser()
        );

    }

    //  GET USER TRANSACTIONS
    public List<Transaction> getUserTransactions(Long userId) {

        if (!urepo.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        return trepo.findByUserId(userId);
    }
    //  CALCULATE BALANCE
    public double calculateBalance(Long userId) {

        List<Transaction> transactions = trepo.findByUserId(userId);

        double balance = 0;

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.INCOME) {
                balance += t.getAmount();
            } else {
                balance -= t.getAmount();
            }
        }

        return balance;
    }

}
