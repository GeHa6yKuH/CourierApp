package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.entity.Courier;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CourierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.2")
            .withInitScript("createTestDB.sql");

    @BeforeAll
    static void setUp() {
        postgres.start();
    }

    @AfterAll
    static void tearDown() {
        postgres.stop();
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCourierByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courier/" + "6453d453-0e33-41ec-aebd-637c5e6bb786")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value("6453d453-0e33-41ec-aebd-637c5e6bb786"));
    }

    @Test
    void createCourierPositiveTest() throws Exception {
        CourierDto courierDto = new CourierDto("Kolia", "+37123895643",
                UUID.fromString("7bdf2f58-17cd-4243-957e-1a3119ff53ad"));
        String requestString = objectMapper.writeValueAsString(courierDto);
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/courier")
                                .content(requestString)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        String JsonResponse = mvcResult.getResponse().getContentAsString();
        Courier courier = objectMapper.readValue(JsonResponse, Courier.class);
        Assertions.assertEquals(courier.getPhoneNumber(), courierDto.getPhoneNumber());
    }

//    @Test
//    void findByIdPositiveTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/product/productId/" + PRODUCT_ID)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(containsString(
//                        PRODUCT_ID)));
//    }
}