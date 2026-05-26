package com.amit8085.finance_tracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class TransactionRequestDTO {
    @Positive(message="Amount Not NUll")
    private Double amount;

    @NotBlank(message="Type is required")
    private String type;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "User ID is required")
    private Long userId;

}
