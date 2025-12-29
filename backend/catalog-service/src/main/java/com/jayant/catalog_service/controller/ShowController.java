package com.jayant.catalog_service.controller;

import com.jayant.catalog_service.dto.ScheduleShowRequest;
import com.jayant.catalog_service.dto.ShowDetailDto;
import com.jayant.catalog_service.dto.ShowResponseDto;
import com.jayant.catalog_service.service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/shows")
@Tag(name = "Show API", description = "Endpoints for managing shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    @Operation(summary = "Schedule a Show")
    public ResponseEntity<ShowResponseDto> scheduleShow(@RequestBody ScheduleShowRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(showService.saveShow(request));
    }

    @GetMapping(params = "city")
    @Operation(summary = "Get all Shows", description = "Returns a list of shows for a specified city, specified movie and specified date.")
    public ResponseEntity<List<ShowResponseDto>> getAllShows(
            @RequestParam String city,
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            ) {
        return ResponseEntity.ok(showService.getAllShows(city, movieId, date));
    }

    @GetMapping(path = "/{showId}")
    @Operation(summary = "Get a Show")
    public ResponseEntity<ShowDetailDto> getShow(@PathVariable Long showId) {
        return ResponseEntity.ok(showService.getShow(showId));
    }

}
