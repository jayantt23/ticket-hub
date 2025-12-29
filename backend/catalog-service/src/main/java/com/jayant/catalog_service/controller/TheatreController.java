package com.jayant.catalog_service.controller;

import com.jayant.catalog_service.dto.TheatreDto;
import com.jayant.catalog_service.service.TheatreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
@Tag(name = "Theatre API", description = "Endpoints for managing theatres")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping
    @Operation(summary = "Add a Theatre")
    public ResponseEntity<TheatreDto> addTheatre(@RequestBody TheatreDto theatreDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(theatreService.saveTheatre(theatreDto));
    }

    @GetMapping(params = "city")
    @Operation(summary = "Get all Theatres", description = "Returns a list of theatres in a specified city.")
    public ResponseEntity<List<TheatreDto>> getTheatres(@RequestParam String city) {
        return ResponseEntity.ok(theatreService.getTheatresByCity(city));
    }

    @GetMapping
    @Operation(summary = "Get all Theatres")
    public ResponseEntity<Page<TheatreDto>> getAllTheatres(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return ResponseEntity.ok(theatreService.getAllTheatresRaw(pageable));
    }

    @GetMapping(path = "/{theatreId}")
    @Operation(summary = "Get a Theatre")
    public ResponseEntity<TheatreDto> getTheatreById(@PathVariable Long theatreId) {
        return ResponseEntity.ok(theatreService.getTheatreById(theatreId));
    }

}
