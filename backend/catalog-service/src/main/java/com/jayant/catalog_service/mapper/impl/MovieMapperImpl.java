package com.jayant.catalog_service.mapper.impl;

import com.jayant.catalog_service.dto.MovieDto;
import com.jayant.catalog_service.entity.Movie;
import com.jayant.catalog_service.mapper.MovieMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieMapperImpl implements MovieMapper {
    @Override
    public MovieDto toDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .description(movie.getDescription())
                .build();
    }
}
