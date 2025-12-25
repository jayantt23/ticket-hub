package com.jayant.catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowResponseDto {
    private Long showId;
    private String movieTitle;
    private String theatreName;
    private LocalDateTime startTime;
}
