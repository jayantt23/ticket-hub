package com.jayant.catalog_service.controller;

import com.jayant.catalog_service.dto.HallDto;
import com.jayant.catalog_service.dto.SeatLayout;
import com.jayant.catalog_service.service.HallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Hall API", description = "Endpoints for managing halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;

    @PostMapping(path = "/theatres/{theatreId}/halls")
    @Operation(summary = "Add a Hall")
    public ResponseEntity<HallDto> addHall(@PathVariable Long theatreId, @RequestBody HallDto hallDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hallService.saveHall(theatreId, hallDto));
    }

    @GetMapping(path = "/theatres/{theatreId}/halls")
    @Operation(summary = "Get all Halls", description = "Returns a list of all halls in a specified Theatre")
    public ResponseEntity<List<HallDto>> getHalls(@PathVariable Long theatreId) {
        return ResponseEntity.ok(hallService.getHallsByTheatreId(theatreId));
    }

    @PutMapping(path = "/halls/{hallId}/seats")
    @Operation(summary = "Update Seats", description = "Updates the seat layout of the specified hall")
    public ResponseEntity<HallDto> updateSeats(@PathVariable Long hallId, @RequestBody SeatLayout seats) {
        return ResponseEntity.ok(hallService.updateSeats(hallId, seats));
    }
}
