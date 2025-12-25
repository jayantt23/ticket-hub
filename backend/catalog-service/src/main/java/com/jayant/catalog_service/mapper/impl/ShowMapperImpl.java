package com.jayant.catalog_service.mapper.impl;

import com.jayant.catalog_service.dto.ShowDto;
import com.jayant.catalog_service.entity.Show;
import com.jayant.catalog_service.mapper.ShowMapper;
import org.springframework.stereotype.Component;

@Component
public class ShowMapperImpl implements ShowMapper {
    @Override
    public Show toEntity(ShowDto showDto) {
        Show show = new Show();
        show.setMovie(null);
        show.setHall(null);
        show.setStartTime(showDto.getStartTime());
        show.setBasePrice(showDto.getBasePrice());

        return show;
    }

    @Override
    public ShowDto toDto(Show show) {
        return ShowDto.builder()
                .id(show.getId())
                .startTime(show.getStartTime())
                .basePrice(show.getBasePrice())
                .build();
    }
}
