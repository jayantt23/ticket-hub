package com.jayant.catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheatreDto implements Serializable {
    private Long id;
    private String name;
    private String city;
    private String address;
    private List<HallDto> halls;
}
