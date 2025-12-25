package com.jayant.catalog_service.controller;

import com.jayant.catalog_service.dto.MovieDto;
import com.jayant.catalog_service.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movieService.saveMovie(movie));
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{movie_id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

}
