package com.bogdan.courierapp.controller.rest;


import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.dto.CourierUpdate;
import com.bogdan.courierapp.entity.Courier;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
class CourierControllerTest {

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

    private final static String VALID_ID = "8035c414-89a8-40e1-a914-83b65388a1f5";
    private final static String NOT_EXIST_VALID_ID = "b5310470-4943-4718-8899-2329a4dec392";

    //---------------------------getCourierById()-----------------------------------------------------
    @Test
    void getCourierByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courier/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_ID));
    }

    @Test
    void getCourierByIdTest404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courier/" + NOT_EXIST_VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void getCourierByIdTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courier/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    //---------------------------getCourierDtoById()---------------------------------------------
    @Test
    void getCourierDtoByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courier/do/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCourierDtoByIdTest404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courier/do/" + NOT_EXIST_VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void getCourierDtoByIdTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courier/do/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    //---------------------------createCourier()-----------------------------------------------------
    @Test
    void createCourierPositiveTest() throws Exception {
        String validId = "7bdf2f58-17cd-4243-957e-1a3119ff53ad";
        CourierDto courierDto = new CourierDto("Kolia", "+37123895643",
                UUID.fromString(validId));
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

    @Test
    void createCourierTest404() throws Exception {
        String deliveryIdNotExist = "2bdf2f58-17cd-4243-957e-1a3119ff53a2";
        CourierDto courierDto = new CourierDto("Kolia", "+37123895643",
                UUID.fromString(deliveryIdNotExist));
        String requestString = objectMapper.writeValueAsString(courierDto);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/courier")
                                .content(requestString)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    //---------------------------updateCourierName()-----------------------------------------------------
    @Test
    void updateCourierNamePositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/courier")
                        .param("id", VALID_ID)
                        .param("name", "Who")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals("Who", getById().getCourierName());
    }

    @Test
    void updateCourierNameTest404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/courier")
                        .param("id", NOT_EXIST_VALID_ID)
                        .param("name", "Who")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void updateCourierNamePositiveTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/courier")
                        .param("id", id)
                        .param("name", "Who")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    //---------------------------updateCourierManager()-----------------------------------------------------
    @Test
    void updateCourierManagerPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/courier/updating")
                        .param("id", VALID_ID)
                        .param("managerId", "4829faa8-4946-410b-b859-f595d537e949")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f5,b5310470-4943-4718-8899-2329a4dec392",
            "b5310470-4943-4718-8899-2329a4dec392,4829faa8-4946-410b-b859-f595d537e949",
    })
    void updateCourierManagerTest404(String id, String managerId) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/courier/updating")
                        .param("id", id)
                        .param("managerId", managerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f, 6829faa8-4946-410b-b859-f595d537e946",
            "6035c414-89a8-40e1-a914-83b65388a1f62, 4829faa8-4946-410b-b859-f595d537e949",
            "6035c414-89a8-40e1-a914-83b65388a1f6, 4829faa8-4946-410b-b859-f595d537e94",
            "6035c414-89a8-40e1-a914-83b65388a1f6, 4829faa8-4946-410b-b859-f595d537e9499",
    })
    void updateCourierManagerTest500(String id, String managerId) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/courier/updating")
                        .param("id", id)
                        .param("managerId", managerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    //---------------------------updateCourierMoreInfoCourierManager()---------------------------------------------
    @Test
    void updateCourierMoreInfoCourierManagerPositiveTest() throws Exception {
        CourierUpdate courierUpdate = new CourierUpdate(UUID.fromString(VALID_ID),
                UUID.fromString("1ced69a9-4918-4ece-a06a-7c7996f4475a"),
                "OFFLINE",
                "+37125679046",
                34.90);
        String requestString = objectMapper.writeValueAsString(courierUpdate);
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/courier/complex")
                                .content(requestString)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //---------------------------deleteCourierManager()---------------------------------------------
    @Test
    void deleteCourierManagerPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courier/delete/" + "6453d453-0e33-41ec-aebd-637c5e6bb786")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCourierManagerTest404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courier/delete/" + NOT_EXIST_VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "8035c414-89a8-40e1-a914-83b65388a1f",
            "8035c414-89a8-40e1-a914-83b65388a1f55",
    })
    void deleteCourierManagerTest500(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courier/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    private Courier getById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/courier/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String courierJson = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(courierJson, Courier.class);
    }
}