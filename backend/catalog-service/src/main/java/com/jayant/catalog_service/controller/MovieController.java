package com.jayant.catalog_service.controller;

import com.jayant.catalog_service.dto.MovieDto;
import com.jayant.catalog_service.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movie API", description = "Endpoints for managing movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @Operation(summary = "Add a Movie")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movieService.saveMovie(movie));
    }

    @GetMapping
    @Operation(summary = "Get all Movies")
    public ResponseEntity<List<MovieDto>> getMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Get a Specified Movie")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

}
