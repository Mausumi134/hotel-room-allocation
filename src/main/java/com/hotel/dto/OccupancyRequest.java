package com.hotel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "Request for calculating room occupancy")
public class OccupancyRequest {
    
    @NotNull
    @Min(0)
    @Schema(description = "Number of premium rooms available", example = "7", minimum = "0")
    private Integer premiumRooms;
    
    @NotNull
    @Min(0)
    @Schema(description = "Number of economy rooms available", example = "5", minimum = "0")
    private Integer economyRooms;
    
    @NotNull
    @Schema(description = "List of potential guest payment amounts in EUR", 
            example = "[23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]")
    private List<Double> potentialGuests;

    public OccupancyRequest() {}

    public OccupancyRequest(Integer premiumRooms, Integer economyRooms, List<Double> potentialGuests) {
        this.premiumRooms = premiumRooms;
        this.economyRooms = economyRooms;
        this.potentialGuests = potentialGuests;
    }

    public Integer getPremiumRooms() {
        return premiumRooms;
    }

    public void setPremiumRooms(Integer premiumRooms) {
        this.premiumRooms = premiumRooms;
    }

    public Integer getEconomyRooms() {
        return economyRooms;
    }

    public void setEconomyRooms(Integer economyRooms) {
        this.economyRooms = economyRooms;
    }

    public List<Double> getPotentialGuests() {
        return potentialGuests;
    }

    public void setPotentialGuests(List<Double> potentialGuests) {
        this.potentialGuests = potentialGuests;
    }
}