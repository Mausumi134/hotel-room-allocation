package com.hotel.service;

import com.hotel.dto.OccupancyRequest;
import com.hotel.dto.OccupancyResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomAllocationService {
    
    private static final double PREMIUM_THRESHOLD = 100.0;

    public OccupancyResponse calculateOccupancy(OccupancyRequest request) {
        List<Double> sortedGuests = request.getPotentialGuests().stream()
                .sorted((a, b) -> Double.compare(b, a)) // Sort descending by price
                .collect(Collectors.toList());

        // Separate premium and economy guests
        List<Double> premiumGuests = sortedGuests.stream()
                .filter(price -> price >= PREMIUM_THRESHOLD)
                .collect(Collectors.toList());

        List<Double> economyGuests = sortedGuests.stream()
                .filter(price -> price < PREMIUM_THRESHOLD)
                .collect(Collectors.toList());

        int premiumRoomsAvailable = request.getPremiumRooms();
        int economyRoomsAvailable = request.getEconomyRooms();

        // Allocate premium guests to premium rooms
        int premiumGuestsAllocated = Math.min(premiumGuests.size(), premiumRoomsAvailable);
        double premiumRevenue = premiumGuests.stream()
                .limit(premiumGuestsAllocated)
                .mapToDouble(Double::doubleValue)
                .sum();

        int remainingPremiumRooms = premiumRoomsAvailable - premiumGuestsAllocated;

        // Allocate economy guests to economy rooms
        int economyGuestsAllocated = Math.min(economyGuests.size(), economyRoomsAvailable);
        double economyRevenue = economyGuests.stream()
                .limit(economyGuestsAllocated)
                .mapToDouble(Double::doubleValue)
                .sum();

        // Smart upgrade: If premium rooms are empty and economy rooms are full
        int upgradedGuests = 0;
        if (remainingPremiumRooms > 0 && economyGuestsAllocated == economyRoomsAvailable) {
            // Get remaining economy guests (those who couldn't get economy rooms)
            List<Double> remainingEconomyGuests = economyGuests.stream()
                    .skip(economyGuestsAllocated)
                    .collect(Collectors.toList());

            // Upgrade the highest-paying remaining economy guests
            upgradedGuests = Math.min(remainingEconomyGuests.size(), remainingPremiumRooms);
            double upgradeRevenue = remainingEconomyGuests.stream()
                    .limit(upgradedGuests)
                    .mapToDouble(Double::doubleValue)
                    .sum();

            premiumRevenue += upgradeRevenue;
        }

        int totalPremiumUsage = premiumGuestsAllocated + upgradedGuests;

        return new OccupancyResponse(totalPremiumUsage, premiumRevenue, economyGuestsAllocated, economyRevenue);
    }
}