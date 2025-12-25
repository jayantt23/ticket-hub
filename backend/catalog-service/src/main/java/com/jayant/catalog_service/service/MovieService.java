package com.jayant.catalog_service.service;

import com.jayant.catalog_service.dto.MovieDto;

import java.util.List;

public interface MovieService {
    MovieDto saveMovie(MovieDto movieDto);
    List<MovieDto> getAllMovies();
    MovieDto getMovieById(Long id);
}