package com.example.springworkshop.repository;

import com.example.springworkshop.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UsersJPARepository extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);

/*
    @Query(value = "Select * From Users where email = :email AND password = :password",nativeQuery = true)
    Users findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
*/

}
