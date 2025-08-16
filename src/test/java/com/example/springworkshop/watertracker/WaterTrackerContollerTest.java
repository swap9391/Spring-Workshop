package com.example.springworkshop.watertracker;

import com.example.springworkshop.model.WaterLogModel;
import com.example.springworkshop.repository.WaterLogJPADataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class WaterTrackerContollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WaterLogJPADataRepository waterLogJPADataRepository;

    @BeforeEach
    void setup() {
        // Clean DB before each test
        waterLogJPADataRepository.deleteAll();
    }

}
