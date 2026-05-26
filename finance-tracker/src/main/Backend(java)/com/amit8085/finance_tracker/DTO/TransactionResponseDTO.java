package com.amit8085.finance_tracker.DTO;

import com.amit8085.finance_tracker.Entity.TransactionType;
import com.amit8085.finance_tracker.Entity.User;
import jakarta.persistence.*;

import java.time.LocalDate;

public class TransactionResponseDTO {

    private Long id;
    private double amount;
    private TransactionType type;
    private String category;
    private LocalDate date;
    private User user;



    public TransactionResponseDTO(double amount, TransactionType type, String category, LocalDate date, User user) {
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}
