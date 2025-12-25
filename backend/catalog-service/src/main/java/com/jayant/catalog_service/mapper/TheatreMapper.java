package com.jayant.catalog_service.mapper;

import com.jayant.catalog_service.dto.TheatreDto;
import com.jayant.catalog_service.entity.Theatre;

public interface TheatreMapper {
    Theatre toEntity(TheatreDto theatreDto);
    TheatreDto toDto(Theatre theatre);
}
