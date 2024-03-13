package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.OrderDto;
import com.bogdan.courierapp.entity.enums.OrderStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    private final static String VALID_ID = "72dff74e-30f1-46cd-9693-42c34a4cac88";

    private final static String VALID_ID_DELETE = "d02ea287-0e06-407f-b1b2-f1cc26651420";


    @Test
    void getOrderByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_ID));
    }

    @Test
    void getOrdersByRestaurantPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order/byRest")
                        .param("restaurantId","86994b48-49a3-4fe9-862b-6da6bd9f869f")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createOrderPositiveTest() throws Exception {
        OrderDto orderDto = new OrderDto(UUID.fromString("86994b48-49a3-4fe9-862b-6da6bd9f869f"),
                OrderStatus.PLACED);
        String requestString = objectMapper.writeValueAsString(orderDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .content(requestString)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void deleteCourierManagerPositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/order/delete/" + VALID_ID_DELETE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}