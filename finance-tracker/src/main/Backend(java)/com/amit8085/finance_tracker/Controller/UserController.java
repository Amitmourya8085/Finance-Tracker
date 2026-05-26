package com.amit8085.finance_tracker.Controller;

import com.amit8085.finance_tracker.ApiResponses.ApiResponse;
import com.amit8085.finance_tracker.DTO.UserRequestDTO;
import com.amit8085.finance_tracker.DTO.UserResponseDTO;
import com.amit8085.finance_tracker.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service){
        this.service=service;
    }
    // CREATING USER
    @PostMapping()
    public ApiResponse<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto){
        return new ApiResponse<>(
                "Success ",
                "User Created ",
                service.createUser(dto)


        );
    }

    //FETCHING ALL USER
    @GetMapping()
    public ApiResponse<List<UserResponseDTO>> getAllUsers(){
        return new ApiResponse<>(
                "Success",
                "Fetched All User",
                service.getAll()
        );
    }


}
