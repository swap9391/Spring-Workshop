package com.example.springworkshop.config;

import com.example.springworkshop.model.Users;
import com.example.springworkshop.repository.UsersJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private final UsersJPARepository usersJPARepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsersJPARepository usersJPARepository, PasswordEncoder passwordEncoder) {
        this.usersJPARepository = usersJPARepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        usersJPARepository.save(new Users(1,"Swapnil Jadhav","swapnil.jadhav@gmail.com",passwordEncoder.encode("Test1234") ,"ADMIN"));
        usersJPARepository.save(new Users(2,"Tom Hanks","tom.hanks@gmail.com",passwordEncoder.encode("Test1234"),"USER"));
        usersJPARepository.save(new Users(3,"Jonny Depp","jonny.depp@gmail.com",passwordEncoder.encode("Test1234"),"USER"));
    }
}
