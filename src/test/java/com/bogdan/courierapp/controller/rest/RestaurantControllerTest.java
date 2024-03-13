package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.entity.Restaurant;
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
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
class RestaurantControllerTest {

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

    private final static String VALID_ID = "86994b48-49a3-4fe9-862b-6da6bd9f869f";

    private final static String VALID_ID_DELETE = "29102365-460d-4301-8117-4e62441d9c7f";

    @Test
    void getRestaurantByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_ID));
    }

    @Test
    void getRestaurantByDeliveryZonePositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rest/byZone/" + "7bdf2f58-17cd-4243-957e-1a3119ff53ad")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        String allRestaurants = mvcResult.getResponse().getContentAsString();
        List<Restaurant> restaurants = objectMapper.readValue(allRestaurants,
                new TypeReference<>() {
                });
        Assertions.assertEquals(2, restaurants.size());
    }

}