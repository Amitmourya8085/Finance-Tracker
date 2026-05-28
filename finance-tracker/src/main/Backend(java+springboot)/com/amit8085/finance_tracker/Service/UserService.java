package com.amit8085.finance_tracker.Service;

import com.amit8085.finance_tracker.DTO.UserRequestDTO;
import com.amit8085.finance_tracker.DTO.UserResponseDTO;
import com.amit8085.finance_tracker.Entity.User;
import com.amit8085.finance_tracker.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo){
        this.repo=repo;
    }
    //CREATE USER
    public UserResponseDTO createUser(UserRequestDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        User saved =repo.save(user);
        return new UserResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }
    //READING ALL USER
    public List<UserResponseDTO> getAll(){
        return repo.findAll()
                .stream()
                .map(user ->new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .toList();
    }
}
