package com.jayant.catalog_service.service.impl;

import com.jayant.catalog_service.dto.MovieDto;
import com.jayant.catalog_service.entity.Movie;
import com.jayant.catalog_service.mapper.MovieMapper;
import com.jayant.catalog_service.repository.MovieRepository;
import com.jayant.catalog_service.service.MovieService;
import jakarta.transaction.Transactional;
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

    private final MovieRepository repository;

    private final MovieMapper mapper;

    @Override
    @CacheEvict(value = "movies", allEntries = true)
    @Transactional
    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = mapper.toEntity(movieDto);

        Movie savedMovie = repository.save(movie);

        return mapper.toDto(savedMovie);
    }

    @Override
    @Cacheable(value = "movies")
    public List<MovieDto> getAllMovies() {
        log.info("Fetching movies from Database...");
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "movies", key = "#id")
    public MovieDto getMovieById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }
}
