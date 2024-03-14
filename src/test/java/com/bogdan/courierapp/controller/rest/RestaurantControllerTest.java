package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.entity.Restaurant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private final static String VALID_ID = "29102365-460d-4301-8117-4e62441d9c7f";

    private final static String VALID_ID_DELETE = "86994b48-49a3-4fe9-862b-6da6bd9f869f";

    private static final String NOT_EXIST_VALID_ID = "f6604fdd-71db-4e8c-a884-61d7de2b40cc";

    //---------------------------getRestaurantById()-----------------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void getRestaurantByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_ID));
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void getCourierByIdTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    //---------------------------getRestaurantsByDeliveryZone()-----------------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void getRestaurantsByDeliveryZonePositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/rest/byZone/" + "7bdf2f58-17cd-4243-957e-1a3119ff53ad")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        String allRestaurants = mvcResult.getResponse().getContentAsString();
        List<Restaurant> restaurants = objectMapper.readValue(allRestaurants,
                new TypeReference<>() {
                });
        Assertions.assertEquals(1, restaurants.size());
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void getRestaurantsByDeliveryZoneTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/byZone/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    //---------------------------getRestaurantsByOwner()-----------------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void getRestaurantsByOwnerPositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rest/byOwner/")
                        .param("owner", "Antony Tilt")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        String allRestaurantsByOwner = mvcResult.getResponse().getContentAsString();
        List<Restaurant> restaurants = objectMapper.readValue(allRestaurantsByOwner,
                new TypeReference<>() {
                });
        Assertions.assertEquals(1, restaurants.size());
    }


    //---------------------------createRest()-----------------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void createRestPositiveTest() throws Exception {
        RestaurantDto restaurantDto = new RestaurantDto("MajorMarket", "Someone");
        String requestString = objectMapper.writeValueAsString(restaurantDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/rest/create")
                        .content(requestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }


    //---------------------------deleteById()-----------------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void deleteByIdPositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/rest/delete/" + VALID_ID_DELETE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void deleteByIdTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/rest/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}