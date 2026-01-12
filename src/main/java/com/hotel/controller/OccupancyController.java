package com.hotel.controller;

import com.hotel.dto.OccupancyRequest;
import com.hotel.dto.OccupancyResponse;
import com.hotel.service.RoomAllocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class OccupancyController {

    private final RoomAllocationService roomAllocationService;

    @Autowired
    public OccupancyController(RoomAllocationService roomAllocationService) {
        this.roomAllocationService = roomAllocationService;
    }

    @PostMapping("/occupancy")
    public ResponseEntity<OccupancyResponse> calculateOccupancy(@Valid @RequestBody OccupancyRequest request) {
        OccupancyResponse response = roomAllocationService.calculateOccupancy(request);
        return ResponseEntity.ok(response);
    }
}