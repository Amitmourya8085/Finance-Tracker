package com.amit8085.finance_tracker.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {
    @NotBlank(message = "Name connot be empty")
    private String name;

    @Email(message = "Enter valid Email id")
    private String email;

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
