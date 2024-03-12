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

    private final static String VALID_ID_FOR_DELETE = "6453d453-0e33-41ec-aebd-637c5e6bb786";

    private final static String VALID_ID = "8035c414-89a8-40e1-a914-83b65388a1f5";

//    @WithMockUser("/some")
    @Test
    void getCourierByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courier/" + VALID_ID)
//                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_ID));
    }
//    @WithMockUser("/some")
    @Test
    void createCourierPositiveTest() throws Exception {
        CourierDto courierDto = new CourierDto("Kolia", "+37123895643",
                UUID.fromString("7bdf2f58-17cd-4243-957e-1a3119ff53ad"));
        String requestString = objectMapper.writeValueAsString(courierDto);
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/courier")
                                .content(requestString)
//                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        String JsonResponse = mvcResult.getResponse().getContentAsString();
        Courier courier = objectMapper.readValue(JsonResponse, Courier.class);
        Assertions.assertEquals(courier.getPhoneNumber(), courierDto.getPhoneNumber());
    }
//    @WithMockUser("/some")
    @Test
    void updateCourierNamePositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/courier")
                        .param("id", VALID_ID)
                        .param("name", "Who")
//                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals("Who", getById().getCourierName());
    }

    @Test
    void updateCourierManagerPositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/courier/updating")
                        .param("id", VALID_ID)
                        .param("managerId", "4829faa8-4946-410b-b859-f595d537e949")
//                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void updateCourierMoreInfoCourierManagerPositiveTest() throws Exception {
        CourierUpdate courierUpdate = new CourierUpdate(UUID.fromString(VALID_ID),
                UUID.fromString("1ced69a9-4918-4ece-a06a-7c7996f4475a"),
                "OFFLINE",
                "+37125679046",
                34.90);
        String requestString = objectMapper.writeValueAsString(courierUpdate);
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.put("/courier/complex")
                                .content(requestString)
//                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void getCourierDtoByIdPositiveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courier/do/" + VALID_ID)
//                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteCourierManagerPositiveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courier/delete/" + VALID_ID_FOR_DELETE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
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