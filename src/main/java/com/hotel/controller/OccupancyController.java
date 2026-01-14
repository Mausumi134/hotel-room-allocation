package com.hotel.controller;

import com.hotel.dto.OccupancyRequest;
import com.hotel.dto.OccupancyResponse;
import com.hotel.service.RoomAllocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Tag(name = "Room Allocation", description = "Hotel room allocation optimization API")
public class OccupancyController {

    private final RoomAllocationService roomAllocationService;

    @Autowired
    public OccupancyController(RoomAllocationService roomAllocationService) {
        this.roomAllocationService = roomAllocationService;
    }

    @PostMapping("/occupancy")
    @Operation(
        summary = "Calculate room occupancy",
        description = "Calculates optimal room allocation for premium and economy rooms based on guest payment willingness. " +
                     "Implements smart upgrade logic where economy guests can be upgraded to premium rooms when beneficial."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully calculated room occupancy",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = OccupancyResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input parameters"
        )
    })
    public ResponseEntity<OccupancyResponse> calculateOccupancy(@Valid @RequestBody OccupancyRequest request) {
        OccupancyResponse response = roomAllocationService.calculateOccupancy(request);
        return ResponseEntity.ok(response);
    }
}