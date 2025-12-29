package com.jayant.catalog_service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SeatRow implements Serializable {
    private String rowLabel;
    private boolean premium;
    private List<Seat> seats;
}
