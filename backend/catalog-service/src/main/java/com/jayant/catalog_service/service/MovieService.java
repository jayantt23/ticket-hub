package com.jayant.catalog_service.service;

import com.jayant.catalog_service.dto.MovieDto;
import com.jayant.catalog_service.entity.Movie;
import com.jayant.catalog_service.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @CacheEvict(value = "movies", allEntries = true)
    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setDescription(movieDto.getDescription());

        Movie savedMovie = movieRepository.save(movie);

        return mapToDto(savedMovie);
    }

    @Cacheable(value = "movies")
    public List<MovieDto> getAllMovies() {
        System.out.println("Fetching movies from Database...");
        return movieRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "movies", key = "#id")
    public MovieDto getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    private MovieDto mapToDto(Movie entity) {
        return MovieDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .genre(entity.getGenre())
                .description(entity.getDescription())
                .build();
    }
}
