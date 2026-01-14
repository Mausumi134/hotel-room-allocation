package com.hotel.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response containing room occupancy calculation results")
public class OccupancyResponse {
    
    @Schema(description = "Number of premium rooms that will be used", example = "6")
    private int usagePremium;
    
    @Schema(description = "Total revenue from premium rooms in EUR", example = "1054.0")
    private double revenuePremium;
    
    @Schema(description = "Number of economy rooms that will be used", example = "4")
    private int usageEconomy;
    
    @Schema(description = "Total revenue from economy rooms in EUR", example = "189.99")
    private double revenueEconomy;

    public OccupancyResponse() {}

    public OccupancyResponse(int usagePremium, double revenuePremium, int usageEconomy, double revenueEconomy) {
        this.usagePremium = usagePremium;
        this.revenuePremium = revenuePremium;
        this.usageEconomy = usageEconomy;
        this.revenueEconomy = revenueEconomy;
    }

    public int getUsagePremium() {
        return usagePremium;
    }

    public void setUsagePremium(int usagePremium) {
        this.usagePremium = usagePremium;
    }

    public double getRevenuePremium() {
        return revenuePremium;
    }

    public void setRevenuePremium(double revenuePremium) {
        this.revenuePremium = revenuePremium;
    }

    public int getUsageEconomy() {
        return usageEconomy;
    }

    public void setUsageEconomy(int usageEconomy) {
        this.usageEconomy = usageEconomy;
    }

    public double getRevenueEconomy() {
        return revenueEconomy;
    }

    public void setRevenueEconomy(double revenueEconomy) {
        this.revenueEconomy = revenueEconomy;
    }
}