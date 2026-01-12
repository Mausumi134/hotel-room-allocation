package com.hotel.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OccupancyRequest {
    
    @NotNull
    @Min(0)
    private Integer premiumRooms;
    
    @NotNull
    @Min(0)
    private Integer economyRooms;
    
    @NotNull
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