package com.gulowsen.vegvesenproxy.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void createMocks () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegnumberTooShortTriggers400Error() throws Exception {
        String invalidRegnumber = "EK";
        ResultActions result = mockMvc.perform(get("/api/vehicleinfo/{regNumber}", invalidRegnumber));
        result.andExpect(status().is4xxClientError());
    }

    @Test
    public void testRegnumberTooLongTriggers400Error() throws Exception {
        String invalidRegnumber = "TOO_LONG_REG";
        ResultActions result = mockMvc.perform(get("/api/vehicleinfo/{regNumber}", invalidRegnumber));
        result.andExpect(status().is4xxClientError());
    }

}
