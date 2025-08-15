package com.example.springworkshop.users.repository;

import com.example.springworkshop.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsersJPARepository extends JpaRepository<Users,Long> {

    @Query(value = "Select * From Users where email = :email AND password = :password",nativeQuery = true)
    Users findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
