package com.jayant.catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto implements Serializable {
    private Long id;
    private String title;
    private String genre;
    private String description;
}
