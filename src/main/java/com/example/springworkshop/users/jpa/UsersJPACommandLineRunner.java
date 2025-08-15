package com.example.springworkshop.users.jpa;

import com.example.springworkshop.users.model.Users;
import com.example.springworkshop.users.repository.UsersJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsersJPACommandLineRunner implements CommandLineRunner {

    @Autowired
    private UsersJPARepository usersJPARepository;

    @Override
    public void run(String... args) throws Exception {
        usersJPARepository.save(new Users(1,"Swapnil Jadhav","swapnil.jadhav@gmail.com","Test1234"));
        usersJPARepository.save(new Users(2,"Tom Hanks","tom.hanks@gmail.com","Test1234"));
        usersJPARepository.save(new Users(3,"Jonny Depp","jonny.depp@gmail.com","Test1234"));
    }
}
