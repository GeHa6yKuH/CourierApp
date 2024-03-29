package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.dto.SupportManagerDto;
import com.bogdan.courierapp.entity.SupportManager;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
class SupportManagerControllerTest {
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

    private final static String VALID_ID = "91069e9d-683b-4fd1-93bc-f135207b7e73";

    private final static String VALID_ID_DELETE = "4829faa8-4946-410b-b859-f595d537e949";

    private final static String INVALID_ID = "29102365-460d-4301-8117-4e62441d9c7f";



    //---------------------------getSupportManagerById()-----------------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void getSupportManagerByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/manager/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_ID));
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void getSupportManagerByIdTest404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/manager/" + INVALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void getSupportManagerByIdTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/manager/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    //---------------------------getManagersWithFirstOrLastName()-----------------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void getManagersWithFirstOrLastNamePositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/manager/nc")
                        .param("firstOrLastName", "Sandra")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
//        String allManagers = mvcResult.getResponse().getContentAsString();
//        List<SupportManager> supportManagers = objectMapper.readValue(allManagers,
//                new TypeReference<>() {
//                });
//        Assertions.assertEquals(1, supportManagers.size());
    }

    //---------------------------getManagersWithName()-----------------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void getManagersWithNamePositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/manager/fa")
                        .param("Name", "Sandra Suar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
//        String allManagers = mvcResult.getResponse().getContentAsString();
//        List<SupportManager> supportManagers = objectMapper.readValue(allManagers,
//                new TypeReference<>() {
//                });
//        Assertions.assertEquals(1, supportManagers.size());
    }

    //---------------------------createRest()-----------------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void createRestPositiveTest() throws Exception {
        SupportManagerDto supportManagerDto = new SupportManagerDto("Dmitri Gol", "136");
        String requestString = objectMapper.writeValueAsString(supportManagerDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/manager")
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
                        .delete("/manager/delete/" + VALID_ID_DELETE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void deleteByIdTest404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/manager/delete/" + INVALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f55",
            "8035c414-89a8-40e1-a914-8yyuyutf4567777545"
    })
    void deleteByIdTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/manager/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}