package com.jayant.catalog_service.mapper;

import com.jayant.catalog_service.dto.MovieDto;
import com.jayant.catalog_service.entity.Movie;

public interface MovieMapper {

    MovieDto toDto(Movie movie);

}
