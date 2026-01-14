package com.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.dto.OccupancyRequest;
import com.hotel.dto.OccupancyResponse;
import com.hotel.service.RoomAllocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OccupancyController.class)
@DisplayName("Occupancy Controller API Tests")
class OccupancyControllerTest {

    @MockBean
    private RoomAllocationService roomAllocationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /occupancy - Success")
    void testOccupancyEndpointSuccess() throws Exception {
        OccupancyRequest request = new OccupancyRequest(
                7, 
                5, 
                Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0)
        );

        OccupancyResponse expectedResponse = new OccupancyResponse(6, 1054.0, 4, 189.99);
        when(roomAllocationService.calculateOccupancy(any(OccupancyRequest.class)))
                .thenReturn(expectedResponse);

        mockMvc.perform(post("/occupancy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.usagePremium").value(6))
                .andExpect(jsonPath("$.revenuePremium").value(1054.0))
                .andExpect(jsonPath("$.usageEconomy").value(4))
                .andExpect(jsonPath("$.revenueEconomy").value(189.99));
    }

    @Test
    @DisplayName("POST /occupancy - Invalid input (negative rooms)")
    void testOccupancyEndpointInvalidInput() throws Exception {
        OccupancyRequest request = new OccupancyRequest(
                -1, 
                5, 
                Arrays.asList(100.0, 200.0)
        );

        mockMvc.perform(post("/occupancy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
