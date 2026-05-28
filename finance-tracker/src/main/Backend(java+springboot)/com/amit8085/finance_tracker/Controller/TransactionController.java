package com.amit8085.finance_tracker.Controller;

import com.amit8085.finance_tracker.ApiResponses.ApiResponse;
import com.amit8085.finance_tracker.DTO.TransactionRequestDTO;
import com.amit8085.finance_tracker.DTO.TransactionResponseDTO;
import com.amit8085.finance_tracker.Entity.Transaction;
import com.amit8085.finance_tracker.Service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService tservice;
    public TransactionController(TransactionService tservice){
        this.tservice = tservice;
    }
    //ADDING TRANSACTION
    @PostMapping
    public ApiResponse<TransactionResponseDTO> addTransaction(@Valid @RequestBody TransactionRequestDTO dto){
        return new ApiResponse<>(
                "Succes",
                "Added Transaction",
                tservice.addTransaction(dto)
        );
    }
    //GET USER TRANSACTION
    @GetMapping("/user/{id}")
    public ApiResponse<List<Transaction>> getUserTransactions(@PathVariable Long id){   //always cheack mismatch problem
        return new ApiResponse<>(
                "Success",
                "Fetched User Transaction",
                tservice.getUserTransactions(id)
        );
    }

    @GetMapping("balance/{id}")
    public ApiResponse<Double> getBalance(@PathVariable Long id){
        return new ApiResponse<>(
                "Success",
                "Fetched Balance",
                tservice.calculateBalance(id)
        );
    }


}
