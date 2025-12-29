package com.jayant.catalog_service.dto;

import com.jayant.catalog_service.entity.Show;
import com.jayant.catalog_service.entity.Theatre;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HallDto implements Serializable {
    private Long id;
    private String name;
    private SeatLayout seatLayout;
    private List<ShowDto> shows;
}
