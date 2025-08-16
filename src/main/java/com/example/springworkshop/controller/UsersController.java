package com.example.springworkshop.controller;

import com.example.springworkshop.model.Users;
import com.example.springworkshop.repository.UsersJPARepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping("/all-users")
    public List<Users> retrieveAllUsers(){
       return usersJPARepository.findAll();
    }

    @RequestMapping("/{id}")
    public Optional<Users> retrieveAllUsers(@PathVariable long id){
        return usersJPARepository.findById(id);
    }

    @RequestMapping("/remove-user/{id}")
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



}
