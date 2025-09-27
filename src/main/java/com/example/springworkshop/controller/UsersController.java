package com.example.springworkshop.controller;

import com.example.springworkshop.dto.UserAddRequestDTO;
import com.example.springworkshop.dto.UserUpdateDTO;
import com.example.springworkshop.model.Users;
import com.example.springworkshop.repository.UsersJPARepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersController {

    UsersJPARepository usersJPARepository;
    PasswordEncoder passwordEncoder;


    public UsersController(UsersJPARepository usersJPARepository, PasswordEncoder passwordEncoder) {
        super();
        this.usersJPARepository = usersJPARepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    @PostMapping("/add-user")
    public ResponseEntity<Object> addUser(@RequestBody UserAddRequestDTO userDTO) {
        Users newUser = new Users();
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setMobileNumber(userDTO.getMobileNumber());
        newUser.setRole(userDTO.getRole());
        usersJPARepository.save(newUser);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully");
        response.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
