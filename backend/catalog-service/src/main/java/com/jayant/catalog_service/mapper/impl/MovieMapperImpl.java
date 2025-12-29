package com.jayant.catalog_service.mapper.impl;

import com.jayant.catalog_service.dto.MovieDto;
import com.jayant.catalog_service.entity.Movie;
import com.jayant.catalog_service.mapper.MovieMapper;
import com.jayant.catalog_service.mapper.ShowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovieMapperImpl implements MovieMapper {

    private final ShowMapper showMapper;

    @Override
    public Movie toEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setDescription(movieDto.getDescription());

        return movie;
    }

    @Override
    public MovieDto toDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .description(movie.getDescription())
                .show(movie.getShow().stream()
                        .map(showMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
