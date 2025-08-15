package com.example.SpringWorkshop.users.repository;

import com.example.SpringWorkshop.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UsersJPARepository extends JpaRepository<Users,Long> {

    @Query(value = "Select * From Users where email = :email AND password = :password",nativeQuery = true)
    Users findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
