package com.jayant.catalog_service.mapper;

import com.jayant.catalog_service.dto.TheatreDto;
import com.jayant.catalog_service.entity.Theatre;

public interface TheatreMapper {
    TheatreDto toDto(Theatre theatre);
}
