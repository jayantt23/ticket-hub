package com.jayant.catalog_service.dto;

import com.jayant.catalog_service.entity.Show;
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
public class MovieDto implements Serializable {
    private Long id;
    private String title;
    private String genre;
    private String description;
    private List<ShowDto> show;
}
