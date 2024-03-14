package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.StatisticsCreateDto;
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

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
class StatisticsControllerTest {

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

    private final static String VALID_ID = "25e65c2d-0d88-41fb-a9b1-085d3a126318";

    private final static String VALID_ID_DELETE = "89e23605-46a6-4e40-b48d-bb677fde1d2c";

    private final static String VALID_COURIER_ID = "dca55a7d-a8db-4777-9daa-97c823277dbf";

    private final static String NO_SUCH_COURIER_ID = "89e23605-46a6-4e40-b48d-bb677fde1d2c";

    private static final String NOT_EXIST_VALID_ID = "f6604fdd-71db-4e8c-a884-61d7de2b40cc";

    //---------------------------getStatisticsById()-----------------------------------------------------
    @Test
    void getStatisticsByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_ID));
    }

    @Test
    void getStatisticsByIdTest404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/" + NOT_EXIST_VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void getStatisticsByIdTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    //---------------------------createStatisticsByDto()-----------------------------------------------------
    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void createStatisticsByDtoPositiveTest() throws Exception {
        StatisticsCreateDto statisticsCreateDto = new StatisticsCreateDto(
                UUID.fromString(VALID_COURIER_ID),
                new Date(System.currentTimeMillis() - 500000),
                new Date(System.currentTimeMillis()),
                233.56
        );
        String requestString = objectMapper.writeValueAsString(statisticsCreateDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/statistics")
                        .content(requestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void createStatisticsByDtoTest404() throws Exception {
        StatisticsCreateDto statisticsCreateDto = new StatisticsCreateDto(
                UUID.fromString(NO_SUCH_COURIER_ID),
                new Date(System.currentTimeMillis() - 500000),
                new Date(System.currentTimeMillis()),
                233.56
        );
        String requestString = objectMapper.writeValueAsString(statisticsCreateDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/statistics")
                        .content(requestString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    //---------------------------deleteStatisticsById()-----------------------------------------------------

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void deleteStatisticsByIdPositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/statistics/delete/" + VALID_ID_DELETE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @Test
    void deleteStatisticsByIdTest404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/statistics/delete/" + NOT_EXIST_VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(username = "admin", password = "admin", roles = {"courier"})
    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f55",
            "8035c414-89a8-40e1-a914-8yyuyutf4567777545"
    })
    void deleteStatisticsByIdTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/statistics/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}