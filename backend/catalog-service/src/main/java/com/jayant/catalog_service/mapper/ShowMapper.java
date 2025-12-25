package com.jayant.catalog_service.mapper;

import com.jayant.catalog_service.dto.ShowDto;
import com.jayant.catalog_service.entity.Show;

public interface ShowMapper {
    Show toEntity(ShowDto showDto);
    ShowDto toDto(Show show);
}
