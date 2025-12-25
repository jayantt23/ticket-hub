package com.jayant.catalog_service.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowDto implements Serializable {
    private Long id;
    private LocalDateTime startTime;
    private BigDecimal basePrice;
}
