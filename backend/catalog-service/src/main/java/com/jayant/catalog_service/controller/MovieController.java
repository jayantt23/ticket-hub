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
    public MovieDto addMovie(@RequestBody MovieDto movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{movie_id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable("movie_id") Long id) {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

}
