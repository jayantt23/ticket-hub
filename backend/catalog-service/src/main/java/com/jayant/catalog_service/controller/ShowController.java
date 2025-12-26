package com.jayant.catalog_service.controller;

import com.jayant.catalog_service.dto.ScheduleShowRequest;
import com.jayant.catalog_service.dto.ShowDetailDto;
import com.jayant.catalog_service.dto.ShowResponseDto;
import com.jayant.catalog_service.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public ResponseEntity<ShowResponseDto> scheduleShow(@RequestBody ScheduleShowRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(showService.saveShow(request));
    }

    @GetMapping(params = "city")
    public ResponseEntity<List<ShowResponseDto>> getAllShows(
            @RequestParam String city,
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            ) {
        return ResponseEntity.ok(showService.getAllShows(city, movieId, date));
    }

    @GetMapping(path = "/{showId}")
    public ResponseEntity<ShowDetailDto> getShow(@PathVariable Long showId) {
        return ResponseEntity.ok(showService.getShow(showId));
    }

}
