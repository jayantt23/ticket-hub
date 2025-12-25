package com.jayant.catalog_service.service.impl;

import com.jayant.catalog_service.dto.MovieDto;
import com.jayant.catalog_service.entity.Movie;
import com.jayant.catalog_service.mapper.MovieMapper;
import com.jayant.catalog_service.repository.MovieRepository;
import com.jayant.catalog_service.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper mapper;

    @Override
    @CacheEvict(value = "movies", allEntries = true)
    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setDescription(movieDto.getDescription());

        Movie savedMovie = movieRepository.save(movie);

        return mapper.toDto(savedMovie);
    }

    @Override
    @Cacheable(value = "movies")
    public List<MovieDto> getAllMovies() {
        log.info("Fetching movies from Database...");
        return movieRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "movies", key = "#id")
    public MovieDto getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }
}
