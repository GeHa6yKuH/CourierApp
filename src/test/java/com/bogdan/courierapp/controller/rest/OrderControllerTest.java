package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.OrderDto;
import com.bogdan.courierapp.entity.enums.OrderStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private static final String NOT_EXIST_VALID_ID = "72dff74e-30f1-46cd-9693-42c34a4cac87";

    //---------------------------getOrderById()---------------------------------------------
    @Test
    void getOrderByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_ID));
    }

    @Test
    void getOrderByIdTest404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order/" + NOT_EXIST_VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void getOrderByIdTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    //---------------------------getOrdersByRestaurant()---------------------------------------------

    @Test
    void getOrdersByRestaurantPositiveTest() throws Exception {
        String id = "86994b48-49a3-4fe9-862b-6da6bd9f869f";
        mockMvc.perform(MockMvcRequestBuilders.get("/order/byRest")
                        .param("restaurantId", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void getOrdersByRestaurantTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order/byRest")
                        .param("restaurantId", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    //---------------------------createOrder()---------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void createOrderPositiveTest() throws Exception {
        OrderDto orderDto = new OrderDto(UUID.fromString("86994b48-49a3-4fe9-862b-6da6bd9f869f"),
                OrderStatus.PLACED);
        String requestString = objectMapper.writeValueAsString(orderDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                        .content(requestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //---------------------------deleteCourierManager()---------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void deleteCourierManagerPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/order/delete/" + VALID_ID_DELETE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void deleteCourierManagerTest404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/order/delete/" + NOT_EXIST_VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void deleteCourierManagerTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/order/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}