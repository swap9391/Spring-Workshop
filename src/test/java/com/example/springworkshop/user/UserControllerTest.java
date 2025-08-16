package com.example.springworkshop.user;

import com.example.springworkshop.model.Users;
import com.example.springworkshop.repository.UsersJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersJPARepository usersJPARepository;

    @Test
    @WithMockUser(username = "swapnil.jadhav@gmail.com", roles = {"ADMIN"})
    void testRetrieveAllUsers_endpointReturnsUsers() throws Exception{

        List<Users> users = new ArrayList<>();
        users.add(new Users("Alice", "alice@example.com", "pass", "USER"));
        users.add(new Users( "Bob", "bob@example.com", "pass", "ADMIN"));
        usersJPARepository.deleteAll();
        usersJPARepository.saveAll(users);

        mockMvc.perform(get("/users/all-users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"));

    }
}
