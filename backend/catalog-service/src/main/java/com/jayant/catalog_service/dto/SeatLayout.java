package com.jayant.catalog_service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SeatLayout implements Serializable {
    private int totalSeats;
    private int columns;
    private List<SeatRow> rows;
}
