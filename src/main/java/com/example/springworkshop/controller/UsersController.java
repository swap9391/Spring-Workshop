package com.example.springworkshop.controller;

import com.example.springworkshop.dto.UserUpdateDTO;
import com.example.springworkshop.model.Users;
import com.example.springworkshop.repository.UsersJPARepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersController {

    public UsersController(UsersJPARepository usersJPARepository) {
        super();
        this.usersJPARepository = usersJPARepository;
    }

    UsersJPARepository usersJPARepository;

    @GetMapping("/all-users")
    public List<Users> retrieveAllUsers(){
       return usersJPARepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Users> retrieveAllUsers(@PathVariable long id){
        return usersJPARepository.findById(id);
    }

    @DeleteMapping("/remove-user/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable long id){

        if (!usersJPARepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with ID " + id + " not found"
            );
        }

        usersJPARepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UserUpdateDTO> updateUser(@PathVariable long id, @RequestBody UserUpdateDTO dto){
       return usersJPARepository.findById(id)
         .map(existingUser ->{
             if (dto.getName() != null) existingUser.setName(dto.getName());
             if (dto.getEmail() != null) existingUser.setEmail(dto.getEmail());
             if (dto.getPassword() != null) existingUser.setPassword(dto.getPassword());
             if (dto.getRole() != null) existingUser.setRole(dto.getRole());

             usersJPARepository.save(existingUser);
           return ResponseEntity.ok(dto);
        })            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User with ID " + id + " not found"));

    }

}
