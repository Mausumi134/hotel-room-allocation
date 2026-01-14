package com.hotel.service;

import com.hotel.dto.OccupancyRequest;
import com.hotel.dto.OccupancyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Room Allocation Service Tests")
class RoomAllocationServiceTest {

    private RoomAllocationService roomAllocationService;
    private List<Double> standardGuests;

    @BeforeEach
    void setUp() {
        roomAllocationService = new RoomAllocationService();
        standardGuests = Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0);
    }

    @Test
    @DisplayName("Test Case 1: 3 Premium, 3 Economy rooms")
    void testCase1_3Premium3Economy() {
        OccupancyRequest request = new OccupancyRequest(3, 3, standardGuests);
        OccupancyResponse response = roomAllocationService.calculateOccupancy(request);

        assertEquals(3, response.getUsagePremium(), "Should use 3 premium rooms");
        assertEquals(738.0, response.getRevenuePremium(), 0.01, "Premium revenue should be 738");
        assertEquals(3, response.getUsageEconomy(), "Should use 3 economy rooms");
        assertEquals(167.99, response.getRevenueEconomy(), 0.01, "Economy revenue should be 167.99");
    }

    @Test
    @DisplayName("Test Case 2: 7 Premium, 5 Economy rooms")
    void testCase2_7Premium5Economy() {
        OccupancyRequest request = new OccupancyRequest(7, 5, standardGuests);
        OccupancyResponse response = roomAllocationService.calculateOccupancy(request);

        assertEquals(6, response.getUsagePremium(), "Should use 6 premium rooms");
        assertEquals(1054.0, response.getRevenuePremium(), 0.01, "Premium revenue should be 1054");
        assertEquals(4, response.getUsageEconomy(), "Should use 4 economy rooms");
        assertEquals(189.99, response.getRevenueEconomy(), 0.01, "Economy revenue should be 189.99");
    }

    @Test
    @DisplayName("Test Case 3: 2 Premium, 7 Economy rooms")
    void testCase3_2Premium7Economy() {
        OccupancyRequest request = new OccupancyRequest(2, 7, standardGuests);
        OccupancyResponse response = roomAllocationService.calculateOccupancy(request);

        assertEquals(2, response.getUsagePremium(), "Should use 2 premium rooms");
        assertEquals(583.0, response.getRevenuePremium(), 0.01, "Premium revenue should be 583");
        assertEquals(4, response.getUsageEconomy(), "Should use 4 economy rooms");
        assertEquals(189.99, response.getRevenueEconomy(), 0.01, "Economy revenue should be 189.99");
    }

    @Test
    @DisplayName("Smart Upgrade: Economy guests upgrade to premium when economy is full")
    void testSmartUpgrade() {
        List<Double> guests = Arrays.asList(30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0);
        OccupancyRequest request = new OccupancyRequest(3, 2, guests);
        OccupancyResponse response = roomAllocationService.calculateOccupancy(request);

        assertEquals(3, response.getUsagePremium(), "Should use 3 premium rooms for upgrades");
        assertEquals(180.0, response.getRevenuePremium(), 0.01, "Premium revenue from upgrades");
        assertEquals(2, response.getUsageEconomy(), "Should use 2 economy rooms");
        assertEquals(170.0, response.getRevenueEconomy(), 0.01, "Economy revenue should be 170");
    }

    @Test
    @DisplayName("No Upgrade: Economy rooms not full")
    void testNoUpgradeWhenEconomyNotFull() {
        List<Double> guests = Arrays.asList(90.0, 80.0, 70.0);
        OccupancyRequest request = new OccupancyRequest(3, 5, guests);
        OccupancyResponse response = roomAllocationService.calculateOccupancy(request);

        assertEquals(0, response.getUsagePremium(), "Should not use premium rooms");
        assertEquals(0.0, response.getRevenuePremium(), 0.01, "No premium revenue");
        assertEquals(3, response.getUsageEconomy(), "Should use 3 economy rooms");
        assertEquals(240.0, response.getRevenueEconomy(), 0.01, "Economy revenue should be 240");
    }

    @Test
    @DisplayName("Boundary Test: 100 EUR is premium")
    void testBoundary100Euro() {
        List<Double> guests = Arrays.asList(99.99, 100.00, 100.01, 50.0);
        OccupancyRequest request = new OccupancyRequest(2, 2, guests);
        OccupancyResponse response = roomAllocationService.calculateOccupancy(request);

        assertEquals(2, response.getUsagePremium(), "100 and 100.01 should be premium");
        assertEquals(200.01, response.getRevenuePremium(), 0.01, "Premium revenue");
        assertEquals(2, response.getUsageEconomy(), "99.99 and 50 should be economy");
        assertEquals(149.99, response.getRevenueEconomy(), 0.01, "Economy revenue");
    }

    @Test
    @DisplayName("Edge Case: No guests")
    void testNoGuests() {
        OccupancyRequest request = new OccupancyRequest(5, 3, Arrays.asList());
        OccupancyResponse response = roomAllocationService.calculateOccupancy(request);

        assertEquals(0, response.getUsagePremium(), "No premium rooms used");
        assertEquals(0.0, response.getRevenuePremium(), 0.01, "No premium revenue");
        assertEquals(0, response.getUsageEconomy(), "No economy rooms used");
        assertEquals(0.0, response.getRevenueEconomy(), 0.01, "No economy revenue");
    }

    @Test
    @DisplayName("Edge Case: No rooms available")
    void testNoRooms() {
        OccupancyRequest request = new OccupancyRequest(0, 0, standardGuests);
        OccupancyResponse response = roomAllocationService.calculateOccupancy(request);

        assertEquals(0, response.getUsagePremium(), "No premium rooms available");
        assertEquals(0.0, response.getRevenuePremium(), 0.01, "No premium revenue");
        assertEquals(0, response.getUsageEconomy(), "No economy rooms available");
        assertEquals(0.0, response.getRevenueEconomy(), 0.01, "No economy revenue");
    }
}
